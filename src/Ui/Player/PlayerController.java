package Ui.Player;

import Database.DBConnection;
import Database.DataController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerController {
    @FXML
    private Rectangle upper_bar, lower_bar;
    @FXML
    private ImageView play, next, prev, loop, like, coverart;
    @FXML
    private Text songname, artistname, time, curtime, back;

    boolean status = false, looping = false, liked = false;         //status: true - playing, false - paused  |  loop: true - loops song, false - no loop
    boolean end = false, initialized = false;
    public static ArrayList<Integer> songids = new ArrayList<Integer>();
    Clip clip;
    InputStream song_data;
    long song_pos;
    float songbarlen;
    int cursong, song_num;
    Stage curstage;
    Scene curscene;
    AudioInputStream ais;
    Connection con;

    public void show_window() throws IOException
    {
        curstage = new Stage();
        Parent page = FXMLLoader.load(getClass().getResource("PlayerScreen.fxml"));
        curstage.initModality(Modality.APPLICATION_MODAL);
        curscene = new Scene(page);
        curstage.setScene(curscene);
        curstage.setTitle("PLAYER");
        curstage.setResizable(false);
        curstage.show();

    }

    private void create_playline()
    {
        Thread bgcounter = new Thread(this::thread_action);
        bgcounter.start();
    }

    private void thread_action()
    {
        while(status) {
            float pos = clip.getMicrosecondPosition() % (clip.getMicrosecondLength() + 1);
            float len = clip.getMicrosecondLength() + 1;
            songbarlen = (float) ((pos / len) * lower_bar.getWidth());
            upper_bar.setWidth(songbarlen);
            double upperbarleft = lower_bar.getLayoutX();
            upper_bar.setLayoutX(upperbarleft);

            time.setText(gettime((long) len));
            curtime.setText(gettime((long) pos));

            if(!looping && status && pos >= len - 5)
                next_button();
            else if(looping && pos >= len - 5)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    private String gettime(long time)
    {
        long seconds = time/1000000;
        int minutes = (int) (seconds/60);
        seconds = seconds%60;
        String rettime = minutes + ":";
        if(seconds<10)
            return rettime + "0" + seconds;
        return rettime + seconds;
    }

    public void initialize()
    {
        try
        {
            DataController db = new DataController();
            con = db.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        while(!initialized){
//            System.out.println("this is the while loop");
//        };
        System.out.println(songids.get(0));
        play_song(songids.get(0));
    }

    private void setsongdata(int song_id)
    {
        try {
            String sql = "select title, name from ALL_SONGS, ALL_ARTISTS where ALL_SONGS.song_id="+ song_id +" and ALL_SONGS.artist_id=ALL_ARTISTS.artist_id";
            Statement smt = con.prepareStatement(sql);
            ResultSet rs = smt.executeQuery(sql);
            rs.next();
            songname.setText(rs.getString("title"));
            artistname.setText(rs.getString("name"));

            sql = "select bitdata from SONG_DATA limit " + song_id + ",1";
            smt = con.prepareStatement(sql);
            rs = smt.executeQuery(sql);
            rs.next();
            song_data = rs.getBlob("bitdata").getBinaryStream();
            
        }catch(Exception e)
        {e.printStackTrace();}
    }

    public void play_song(int song_id)
    {
        if(status)
            clip.stop();
        try {
            System.gc();
            setsongdata(song_id);
            ais = AudioSystem.getAudioInputStream(song_data);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            status = true;
            create_playline();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play_pause_button()
    {
        if(status) {
            song_pos = clip.getMicrosecondPosition();
            clip.stop();
            status = false;
            play.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/pause.png")));
        }
        else
        {
            clip.setMicrosecondPosition(song_pos % clip.getMicrosecondLength());
            clip.start();
            status = true;
            play.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/play.png")));
            create_playline();
        }
    }

    public void next_button()
    {
        cursong++;
        if(cursong > song_num)
            cursong = 1;
        clip.stop();
        if(!status)
        {
            status = true;
            play.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/play.png")));
            create_playline();
        }
        status = false;
        play_song(songids.get(cursong));
    }

    public void prev_button()
    {
        if(clip.getMicrosecondPosition()%clip.getMicrosecondLength() >= 3000000)
            clip.setMicrosecondPosition(0);
        else
        {
            cursong--;
            if(cursong < 0)
                cursong = song_num;
            clip.stop();
            if(!status)
            {
                status = true;
                Image playing = new Image(getClass().getResourceAsStream("Assets\\Icons\\PlayerIcons\\pause.png"));
                play.setImage(playing);
                create_playline();
            }
            status = false;
            play_song(songids.get(cursong));
        }
    }

    public void loop_button()
    {
        if(looping)
        {
            looping = false;
            loop.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/loop2.png")));
        }
        else
        {
            looping = true;
            loop.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/loop1.png")));
        }
    }

    public void like_button()
    {
        if(!liked) {
            like.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/like2.png")));
            liked = true;
        }
        else
        {
            like.setImage(new Image(getClass().getResourceAsStream("@../../Assets/Icons/Player Icons/like1.png")));
            liked = false;
        }
    }

    public void exit()
    {
        end = true;
        Stage cur = (Stage)(upper_bar.getScene().getWindow());
        cur.close();
    }

    public void play_single_song(int s_id){
        try {
            songids.add(s_id);
            song_num = 1;
            show_window();
            initialized = true;
        }catch(Exception e){e.printStackTrace();}
    }
    public void play_playlist(int p_id)
    {
        try {
            this.initialized = true;
            DBConnection db = new DBConnection();
            con = db.getConnection();
            String sql = "select song_id from PLAYLIST_SONGS where playlist_id=" + p_id;
            Statement smt = con.prepareStatement(sql);
            ResultSet rs = smt.executeQuery(sql);
            while(rs.next()) {
                songids.add(rs.getInt("song_id"));
                System.out.println(rs.getInt("song_id"));
            }
            song_num = songids.size();
            show_window();

        }catch(Exception e)
        {e.printStackTrace();}
    }
}

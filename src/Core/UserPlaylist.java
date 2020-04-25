package Core;

public class UserPlaylist {
    private int user_id;
    private int playlist_id;
    private String name;
    private int song_num;

    public UserPlaylist(int user_id, int playlist_id, String name){
        this.user_id = user_id;
        this.playlist_id = playlist_id;
        this.name = name;
        this.song_num = 0;
    }

    public int getPlaylist_id() {
        return playlist_id;
    }

    public int getSong_num() {
        return song_num;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

}


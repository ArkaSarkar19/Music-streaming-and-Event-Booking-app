package Core;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;
import Exception.*;
import java.util.regex.Pattern;

public class User{
    private int user_id;
    private String name;
    private String country;
    private String email;
    private String DOB;
    private String gender;
    public User(int user_id, String name, String country, String email, String DOB, String gender){
        this.user_id = user_id;
        this.name = name;
        this.country = country;
        this.email = email;
        this.DOB = DOB;
        this.gender = gender;

    }
    public void checkFormatting() throws MyException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches()) throw new InvalidEmailException("Enter a correct email address");

        try {
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            df.setLenient(false);
            df.parse(DOB);
        }
        catch (ParseException e){
            throw new IncorrectDateException("Enter a valid date");
        }

    }
    public void display(){
        System.out.println(user_id + " " + name + " " + country + " " + email + " " + DOB + " " + gender);

    }
    public int getAge(){
        return Calendar.DATE;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getCountry() {
        return country;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}

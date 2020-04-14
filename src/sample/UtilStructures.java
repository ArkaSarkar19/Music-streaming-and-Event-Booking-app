package sample;
import java.sql.*;
import java.util.regex.Pattern;

class User{
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
    public void checkFormatting() throws  MyException{
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if(!pat.matcher(email).matches()) throw new InvalidEmailException("Enter a correct email address");
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
}

class UserAuth{
    private int user_id;
    private String password;

    public UserAuth(int user_id,String password){
        this.user_id = user_id;
        this.password = password;
    }

//    Be between 8 and 40 characters long
//    Contain at least one digit.
//    Contain at least one lower case character.
//    Contain at least one upper case character.
//    Contain at least on special character from [ @ # $ % ! . ].

    public void checkFormatting() throws  MyException{
        String passRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
        Pattern pat = Pattern.compile(passRegex);
        if(!pat.matcher(password).matches()) throw new InvalidPasswordException("Enter a correct password");

    }

    public int getUser_id() {
        return user_id;
    }

    public String getPassword() {
        return password;
    }

}
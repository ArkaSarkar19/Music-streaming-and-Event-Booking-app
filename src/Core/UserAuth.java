package Core;
import java.util.regex.Pattern;
import Exception.*;


public class UserAuth{
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
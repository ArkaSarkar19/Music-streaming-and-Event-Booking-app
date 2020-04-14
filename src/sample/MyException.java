package sample;

public class MyException extends Exception{
    public  MyException(String message){
        super(message);
    }
}
class InvalidTableNameException extends MyException{

    public InvalidTableNameException(String message) {
        super(message);
    }
}

class ConnectionInvalidException extends MyException{
    public ConnectionInvalidException(String message) {
        super(message);
    }
}

class InvalidUsernamePassowordException extends  MyException{
    public InvalidUsernamePassowordException(String message){
        super(message);
    }
}

class InvalidSignupException extends  MyException{
    public InvalidSignupException(String message) {
        super(message);
    }
}

class UserExistsException extends MyException{

    public UserExistsException(String message) {
        super(message);
    }
}

class InvalidEmailException extends  MyException{

    public InvalidEmailException(String message) {
        super(message);
    }
}
class InvalidPasswordException extends  MyException{

    public InvalidPasswordException(String message) {
        super(message);
    }
}
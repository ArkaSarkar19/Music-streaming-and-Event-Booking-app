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
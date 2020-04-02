package com.example.bajao;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class Controller {

    public static void validateLogin(String username, String password) throws MyException{
        DataController db = new DataController();
        db.getlogin(username,password);

    }
}

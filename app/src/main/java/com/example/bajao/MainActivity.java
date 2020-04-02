package com.example.bajao;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static Button LoginButton;
    public static EditText LoginPassword;
    public static EditText LoginUsername;
    public static TextView loginPageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void login(){
        LoginButton = (Button)findViewById(R.id.LoginButton);
        LoginPassword = (EditText)findViewById(R.id.loginPassword);
        LoginUsername = (EditText)findViewById(R.id.LoginUsername);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = LoginUsername.getText().toString();
                String password = LoginPassword.getText().toString();
                try {
                    Controller.validateLogin(username,password);
                    System.out.println("Sucess");
                } catch (ConnectionInvalidException e) {
                    System.out.println(e.getMessage());
                }
                catch (InvalidUsernamePassowordException e ){
                    System.out.println(e.getMessage());
                }
                catch (MyException e){
                    e.printStackTrace();
                }

            }
        });

    }
}

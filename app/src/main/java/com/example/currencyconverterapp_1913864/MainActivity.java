package com.example.currencyconverterapp_1913864;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edText,edText2;
    Button btnLogin;
    String email = "alex@gmail.com";
    String password = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {

        edText = findViewById(R.id.edText);
        edText2 = findViewById(R.id.edText2);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        ResetScreenComponents();

    }

    private void ResetScreenComponents() {
        edText.setText(null);
        edText2.setText(null);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id== R.id.btnLogin){
            Login();
        }
    }

    private void Login() {
        String emailText = edText.getText().toString();
        String passwordText = edText2.getText().toString();
        if (emailText.isEmpty()) {
            Toast.makeText(this, "Empty E-Mail!", Toast.LENGTH_LONG).show();
        }
        else if (passwordText.isEmpty()) {
            Toast.makeText(this, "Empty Password!", Toast.LENGTH_LONG).show();
        }
        else {
            if (emailText.equals(email)) {
                if (passwordText.equals(password)) {
                    goToConverterActivity();
                }
                else {
                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "Invalid E-Mail", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToConverterActivity() {
        Intent intent = new Intent(this,ConverterActivity.class);
        startActivity(intent);
    }

}

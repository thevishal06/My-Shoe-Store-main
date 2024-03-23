package com.example.myshoestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private  EditText Email;
    private EditText Password;
    private Button Login;
    private Button signUp;
    private FirebaseAuth auth;

   // Button signIn,signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login= findViewById(R.id.Login);
        Email = findViewById(R.id.login_email);
        Password = findViewById(R.id.login_pass);
        signUp = findViewById(R.id.reg_sign_up);
        auth = FirebaseAuth.getInstance();

        //Button login=(Button) findViewById(R.id.sign_in_btn);

        Login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Email1 = Email.getText().toString();
                        String Password1 = Password.getText().toString();
                        if(TextUtils.isEmpty(Email1) || TextUtils.isEmpty(Password1)){
                            Toast.makeText(LoginActivity.this, "Please enter Email and Password", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Login2(Email1,Password1);
                        }
                        //Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        //startActivity(i);
                    }

                    private void Login2(String email1, String password1) {
                        auth.signInWithEmailAndPassword(email1,password1).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        });
                    }
                }
        );
        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(LoginActivity.this, RegistrationActivity.class);
                        startActivity(i);
                    }
                }
        );
    }
}
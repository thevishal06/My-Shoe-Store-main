package com.example.myshoestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    private  EditText Email;
    private EditText  Password;
    private Button signUp;
    private FirebaseAuth auth;




    Button signIn;
    EditText Name;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUp = findViewById(R.id.signUp_btn);
        Name = findViewById(R.id.reg_name);
        Password = findViewById(R.id.reg_password);
        Email = findViewById(R.id.reg_email);
        signIn = findViewById(R.id.reg_signin);
        auth = FirebaseAuth.getInstance();



        //Button signUp =(Button) findViewById(R.id.signUp_btn);

        signUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, Object> m = new HashMap<String,Object>();
                        m.put("Name",Name.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Vendor1").push().setValue(m);
                        String Email1 = Email.getText().toString();
                        String Password1 = Password.getText().toString();
                        if(TextUtils.isEmpty(Email1) || TextUtils.isEmpty(Password1)){
                            Toast.makeText(RegistrationActivity.this,"Enter Email or Password Both", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            regis(Email1,Password1);
                        }
                        //Intent i=new Intent(RegistrationActivity.this, MainActivity.class);
                        //startActivity(i);
                    }
                }
        );
        /*Button signIn = (Button)findViewById(R.id.reg_signin);

        signIn.setOnTouchListener(
                new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(RegistrationActivity.this , LoginActivity.class);
                startActivity(i);
                return true;
            }
        }
        );*/
        signIn.setOnClickListener(
                new View.OnClickListener() {
                        @Override
                        public void onClick(View v){
                            Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(i);
        }
                }
        );

    }

    private void regis(String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this,"Successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(RegistrationActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
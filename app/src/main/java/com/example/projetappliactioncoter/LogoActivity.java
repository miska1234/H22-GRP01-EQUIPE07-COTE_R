package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    Timer timer;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        //Fait en sorte de keep le user logged in
        //TODO faire en sorte qu il ne peut pas back button a n importe kelle page avec onBackButtonPressed()
        mAuth = FirebaseAuth.getInstance();


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(mAuth.getCurrentUser() != null){
                    startActivity(new Intent(LogoActivity.this, MenuV2Activity.class));
                } else {
                    startActivity(new Intent(LogoActivity.this, AcceuilleActivity.class));
                }

            }
        }, 3000);


    }
}
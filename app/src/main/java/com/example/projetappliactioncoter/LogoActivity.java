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

        //TODO Faire en sorte que le user get connected automatiquement apres avoir sign in et pour ca,
        // il faut un bhy sign out dans le menu qu on ouvre au debut
        //mAuth = FirebaseAuth.getInstance();
        //user = FirebaseAuth.getInstance().getCurrentUser();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoActivity.this, AcceuilleActivity.class);
                startActivity(intent);
            }
        }, 3000);


    }
}
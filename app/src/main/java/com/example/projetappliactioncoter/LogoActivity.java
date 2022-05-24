package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    private Timer timer;
    FirebaseAuth mAuth;
    FirebaseUser user;

    /**
     * Créer l’activité de la page où l’on peut voir le logo pour quelque seconde
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        mAuth = FirebaseAuth.getInstance();


        timer = new Timer();

        //Laisser le logo pendant 3 secondes
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                //Vérifie si il y a déjà un compte connecter et
                //si il y en a un, changer d’activité à celle du menu, sinon, à celle de l’acceuille
                if(mAuth.getCurrentUser() != null){
                    startActivity(new Intent(LogoActivity.this, MenuV2Activity.class));
                } else {
                    startActivity(new Intent(LogoActivity.this, AcceuilleActivity.class));
                }

            }
        }, 3000);


    }
}
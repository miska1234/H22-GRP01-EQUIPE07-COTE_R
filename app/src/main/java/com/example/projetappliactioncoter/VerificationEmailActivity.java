package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerificationEmailActivity extends AppCompatActivity {

    private Button bouttonRetourAcceuil;

    /**
     * Créer l’activité de la page qui nous demande de vérifier notre email
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_email);

        //Relier le boutton pour retourner à l’acceuille
        bouttonRetourAcceuil = findViewById(R.id.buttonRetourALacceuil);
    }

    /**
     * Méthode qui permet de retourner à la page d’acceuille
     * @param view
     */
    public void onClick(View view) {
        Intent intent = new Intent(VerificationEmailActivity.this, ConnectionActivity.class);
        startActivity(intent);
    }
}
package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bouttonEnregistrer, boutonConnecter;

    /**
     * Créer l’activité de la page d’acceuille
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuille);

        //Initialiser les boutons
        boutonConnecter = findViewById(R.id.buttonAllerSeConnecter);
        bouttonEnregistrer = findViewById(R.id.buttonAllerSenregistrer);

        //Set la méthode OnClick
        boutonConnecter.setOnClickListener(this);
        bouttonEnregistrer.setOnClickListener(this);
    }

    /**
     * Méthode qui sert à change d’activité si l’on clique sur un boutton
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){

            //Changer de la page d’acceuille à la page pour se connecter
            case R.id.buttonAllerSeConnecter:
                Intent intentConnecter = new Intent(AcceuilleActivity.this, ConnectionActivity.class);
                startActivity(intentConnecter);
                break;

            //Changer de la page d’acceuille à la page pour s’inscrire
            case R.id.buttonAllerSenregistrer:
                Intent intentEnregistrer = new Intent(AcceuilleActivity.this, EnregistrementV2Activity.class);
                startActivity(intentEnregistrer);
                break;

        }
    }
}
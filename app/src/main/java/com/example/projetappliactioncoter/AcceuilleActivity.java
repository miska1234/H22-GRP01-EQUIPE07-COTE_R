package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bouttonEnregistrer, boutonConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuille);

        boutonConnecter = findViewById(R.id.buttonAllerSeConnecter);
        bouttonEnregistrer = findViewById(R.id.buttonAllerSenregistrer);

        boutonConnecter.setOnClickListener(this);
        bouttonEnregistrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonAllerSeConnecter:
                Intent intentConnecter = new Intent(AcceuilleActivity.this, ConnectionActivity.class);
                startActivity(intentConnecter);
                break;
            case R.id.buttonAllerSenregistrer:
                Intent intentEnregistrer = new Intent(AcceuilleActivity.this, EnregistrementV2Activity.class);
                startActivity(intentEnregistrer);
                break;

        }
    }
}
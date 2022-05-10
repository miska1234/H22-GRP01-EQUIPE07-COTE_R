package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class AcceuilleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bouttonEnregistrer, boutonConnecter, boutonLangueFrancais, boutonLangueAnglais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuille);
        LanguageManager lang = new LanguageManager(this);
        boutonConnecter = findViewById(R.id.buttonAllerSeConnecter);
        bouttonEnregistrer = findViewById(R.id.buttonAllerSenregistrer);
        boutonLangueFrancais = findViewById(R.id.francais1);
        boutonLangueAnglais = findViewById(R.id.anglais1);

        boutonConnecter.setOnClickListener(this);
        bouttonEnregistrer.setOnClickListener(this);
        boutonLangueAnglais.setOnClickListener(view -> {
            lang.updateResource("en");
            recreate();
        });
        boutonLangueFrancais.setOnClickListener(view -> {
            lang.updateResource("fr");
            recreate();
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
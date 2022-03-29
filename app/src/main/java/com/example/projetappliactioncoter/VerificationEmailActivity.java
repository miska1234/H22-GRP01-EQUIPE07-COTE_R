package com.example.projetappliactioncoter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerificationEmailActivity extends AppCompatActivity {

    Button bouttonRetourAcceuil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_email);

        bouttonRetourAcceuil = findViewById(R.id.buttonRetourALacceuil);
    }

    public void onClick(View view) {
        Intent intent = new Intent(VerificationEmailActivity.this, ConnectionActivity.class);
        startActivity(intent);
    }
}
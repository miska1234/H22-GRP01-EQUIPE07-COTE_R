package com.example.projetappliactioncoter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ConnectionActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText email, motDePasse;
    private TextView motDePasseOublier, sEnregistrer;
    private Button buttonConnecter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        email = findViewById(R.id.editTextEmailAddress);
        motDePasse = findViewById(R.id.editTextPassword);
        motDePasseOublier = findViewById(R.id.textViewMotPasseOblier);
        sEnregistrer = findViewById(R.id.textViewSenregistrer);
        buttonConnecter = findViewById(R.id.buttonSeConnecter);
        mAuth = FirebaseAuth.getInstance();

        buttonConnecter.setOnClickListener(this);
        motDePasseOublier.setOnClickListener(this);
        sEnregistrer.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonSeConnecter:
                seConnecter();
                break;
            case R.id.textViewMotPasseOblier:
                //TODO faire le activity de motdepasseoublier

                Intent switchToReset = new Intent(ConnectionActivity.this, ResetPassword.class);
                startActivity(switchToReset);

                break;
            case R.id.textViewSenregistrer:
                Intent switchToEnregistrer = new Intent(ConnectionActivity.this, EnregistrementV2Activity.class);
                startActivity(switchToEnregistrer);
                break;
        }
    }

    public void seConnecter(){

        //TODO ADD une progress bar

        String emailTexte = email.getText().toString().trim();
        String motDePasseTexte = motDePasse.getText().toString().trim();

        if(emailTexte.isEmpty() || motDePasseTexte.isEmpty()){
              Toast.makeText(ConnectionActivity.this, "Champs de texte vide, veuillez les remplirs!", Toast.LENGTH_SHORT).show();

        }else{
            mAuth.signInWithEmailAndPassword(emailTexte, motDePasseTexte).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if(user.isEmailVerified()){
                            Intent switchToMenu = new Intent(ConnectionActivity.this, MenuV2Activity.class);
                            startActivity(switchToMenu);
                        } else {
                            user.sendEmailVerification();
                            Toast.makeText(ConnectionActivity.this, "Votre email n’est pas vérifier, allez le vérifier live!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ConnectionActivity.this, "Erreur dans votre saisie de mot de passe ou email! Veuillez reesayer", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}
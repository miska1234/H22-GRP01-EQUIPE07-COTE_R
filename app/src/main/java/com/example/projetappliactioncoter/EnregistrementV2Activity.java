package com.example.projetappliactioncoter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;


public class EnregistrementV2Activity extends AppCompatActivity {

    private EditText nomComplet, age, ecole, programme, email, motDePasse;
    private TextView dejaCompte;
    private Button enregistrer;
    private FirebaseAuth mAuth;

    //TODO mettre les noms d ecoles et de programme dans une liste et faire bhy banderolz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_v2);

        nomComplet = findViewById(R.id.editNomComplet);
        age = findViewById(R.id.editTextAge);
        ecole = findViewById(R.id.editTextEcole);
        programme = findViewById(R.id.editTextProgramme);
        email = findViewById(R.id.editTextEmailAddress);
        motDePasse = findViewById(R.id.editTextPassword);
        dejaCompte = findViewById(R.id.textViewDejaCompte);

        enregistrer = findViewById(R.id.buttonSenregistrer);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onClick(View view) {
        EnregistrerUtilisateur();
    }

    public void onClickDejaCompte(View view) {
        Intent switchToConnection = new Intent(EnregistrementV2Activity.this, ConnectionActivity.class);
        startActivity(switchToConnection);
    }

    public void EnregistrerUtilisateur(){
        String nomComplet = this.nomComplet.getText().toString().trim();
        String age = this.age.getText().toString().trim();
        String ecole = this.ecole.getText().toString().trim(); //Faire une bande roulante avc tt les skool
        String programme = this.programme.getText().toString().trim(); //Faire une bande roulante avc tt les programmes
        String email = this.email.getText().toString().trim();
        String motDePasse = this.motDePasse.getText().toString().trim();

        if(nomComplet.isEmpty()){
            this.nomComplet.setError(getString(R.string.nomCompletRequis));
            this.nomComplet.requestFocus();
            return;
        }

        //Faire la vérification de l’age
        if(age.isEmpty()){
            this.age.setError(getString(R.string.ageRequis));
            this.age.requestFocus();
            return;
        }
        if(ecole.isEmpty()){
            this.ecole.setError(getString(R.string.ecoleRequis));
            this.ecole.requestFocus();
            return;
        }
        if(programme.isEmpty()){
            this.programme.setError(getString(R.string.programmeRequis));
            this.programme.requestFocus();
            return;
        }
        if(email.isEmpty()){
            this.email.setError(getString(R.string.emailRequis));
            this.email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.email.setError(getString(R.string.emailNonValide));
            this.email.requestFocus();
            return;
        }

        if(!passwordVerifier(motDePasse)){
            this.motDePasse.setError(getString(R.string.motDePasseRequis));
            this.motDePasse.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, motDePasse)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Utilisateur utilisateur = new Utilisateur(nomComplet, age, ecole, programme, email);

                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification();
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(utilisateur).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(EnregistrementV2Activity.this, getString(R.string.UtilisateurEnregistrer), Toast.LENGTH_SHORT).show();
                                        Intent switchToProfil = new Intent(EnregistrementV2Activity.this, VerificationEmailActivity.class);
                                        startActivity(switchToProfil);
                                    } else{
                                        Toast.makeText(EnregistrementV2Activity.this, getString(R.string.UtilisateurNonEnregistrer), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(EnregistrementV2Activity.this, getString(R.string.UtilisateurNonEnregistrer), Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }

    private boolean passwordVerifier(String password){


        int captialLetter = 0;
        int numbers = 0;
        int specialChar = 0;
        int space = 0;

        for(int i = 0 ; i < password.length() ; i++){
            if(password.charAt(i) >= 65 && password.charAt(i) <= 90){
                captialLetter++;
            } else if(password.charAt(i) >= 48 && password.charAt(i) <= 57){
                numbers++;
            } else if (password.charAt(i) >= 33 && password.charAt(i) <= 47){
                specialChar++;
            } else if(password.charAt(i) == 32){
                space++;
            }
        }

        if(password.length() < 6){
            return false;
        }
        if(captialLetter ==0 || numbers == 0 || specialChar == 0){
            return false;
        }
        if(space > 0 ) {
            return false;
        }
        return true;
    }


}
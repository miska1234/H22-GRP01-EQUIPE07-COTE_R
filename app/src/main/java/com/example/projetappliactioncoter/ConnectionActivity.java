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

    /**
     * Créer l’activité de la page où l’on peut voir toutes nos cote r
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        //Initialisation de toutes les différents objets dans notre fichier .xml
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


    /**
     * Méthode OnClick pour toutes les bouttons
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){

            //Le boutton se connnecter appelle la méthode qui vérifie la connxion
            case R.id.buttonSeConnecter:
                seConnecter();
                break;

            //Le text view de mot de passe oublier permet de changer de fragment à celui ou l’on peut changer le mdp
            case R.id.textViewMotPasseOblier:

                Intent switchToReset = new Intent(ConnectionActivity.this, ResetPassword.class);
                startActivity(switchToReset);

                break;

            //Le text view s’enregistrer permet de revenir sur la page de l’enregistrement si l’on a fait une erreur et qu’on a pas de compte
            case R.id.textViewSenregistrer:
                Intent switchToEnregistrer = new Intent(ConnectionActivity.this, EnregistrementV2Activity.class);
                startActivity(switchToEnregistrer);
                break;
        }
    }

    /**
     * Méthode vérifiant si les informations entrer sont correct et sont présentes dans le firebase
     */
    public void seConnecter(){



        String emailTexte = email.getText().toString().trim();
        String motDePasseTexte = motDePasse.getText().toString().trim();

        //Vérifie si le champs de texte est vide
        if(emailTexte.isEmpty() || motDePasseTexte.isEmpty()){
              Toast.makeText(ConnectionActivity.this, "Champs de texte vide, veuillez les remplirs!", Toast.LENGTH_SHORT).show();


        }else{
            //Méthode provided par firebase pour vérifier la connexion d’un utilisateur
            mAuth.signInWithEmailAndPassword(emailTexte, motDePasseTexte).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                /**
                 * Méthode qui change de fragment après la vérification du email et du mot de passe
                 * @param task
                 */
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        //Si le email et mdp est bon, switch le fragment à celui du menu
                        if(user.isEmailVerified()){
                            Intent switchToMenu = new Intent(ConnectionActivity.this, MenuV2Activity.class);
                            startActivity(switchToMenu);

                        //Sinon, envoyer un email de vérification et lui mettre une erreur
                        } else {
                            user.sendEmailVerification();
                            Toast.makeText(ConnectionActivity.this, "Votre email n’est pas vérifier, allez le vérifier live!", Toast.LENGTH_SHORT).show();
                        }

                    //Si le email ou le mdp est mauvais, montrer une erreur
                    } else {
                        Toast.makeText(ConnectionActivity.this, "Erreur dans votre saisie de mot de passe ou email! Veuillez reesayer", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class ResetPassword extends AppCompatActivity {

    private TextView textReinitialiser;
    private Button buttonReinitialiser;
    private EditText editTextReinitialiser;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        textReinitialiser = findViewById(R.id.textViewResetPassword);
        buttonReinitialiser = findViewById(R.id.buttonReset);
        editTextReinitialiser = findViewById(R.id.editTextResetEmailAddress);

        mAuth = FirebaseAuth.getInstance();

        buttonReinitialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reinitialiser();
            }
        });
    }

    public void reinitialiser(){
        String emailReset = editTextReinitialiser.getText().toString().trim();

        if(emailReset.isEmpty()){
            editTextReinitialiser.setError("Le email est requis!");
            editTextReinitialiser.requestFocus();

            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailReset).matches()){
            editTextReinitialiser.setError("Le email n’est pas valide. Réessayer!");
            editTextReinitialiser.requestFocus();

            return;
        }

        mAuth.sendPasswordResetEmail(emailReset).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "Veuillez regarder votre boite courriel!", Toast.LENGTH_LONG).show();
                    Intent switchToConnection = new Intent(ResetPassword.this, ConnectionActivity.class);
                    startActivity(switchToConnection);
                }else{
                    Toast.makeText(ResetPassword.this, "Veuillez recommencer, le courriel rentré n’est pas bon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
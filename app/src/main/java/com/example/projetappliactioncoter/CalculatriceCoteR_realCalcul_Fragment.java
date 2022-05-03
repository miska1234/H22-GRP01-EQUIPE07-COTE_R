package com.example.projetappliactioncoter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CalculatriceCoteR_realCalcul_Fragment extends Fragment {

    EditText matiere, note, moyenne, ecartType, moyenneSecondaire, nbr_unites;
    Button ajouter_modierButton;
    FirebaseUser user;
    DatabaseReference reference;
    String userID;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculatricecoter_realcalcul, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        matiere = view.findViewById(R.id.matiere_calculatrice_cote_r);
        note = view.findViewById(R.id.note_calculatrice_cote_r);
        moyenne = view.findViewById(R.id.moyenne_calculatrice_cote_r);
        ecartType = view.findViewById(R.id.ecart_type_calculatrice_cote_r);
        moyenneSecondaire = view.findViewById(R.id.moyenne_secondaire_calculatrice_cote_r);
        ajouter_modierButton = view.findViewById(R.id.ajouterModifiercoter);
        nbr_unites = view.findViewById(R.id.nbr_unites_calculatrice_cote_r);

        ajouter_modierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterCoteRVerification();

            }
        });

        return view;
    }


    public void ajouterCoteRVerification() {
        String matiere = this.matiere.getText().toString().trim();
        String note = this.note.getText().toString().trim();
        String moyenne = this.moyenne.getText().toString().trim();
        String ecartType = this.ecartType.getText().toString().trim();
        String moyenneSecondaire = this.moyenneSecondaire.getText().toString().trim();
        String nbrUnites = this.nbr_unites.getText().toString().trim();

        if (matiere.isEmpty()) {
            this.matiere.setError("Matière manquante");
            this.matiere.requestFocus();
            return;
        }

        if (note.isEmpty()) {
            this.note.setError("Note manquante");
            this.note.requestFocus();
            return;
        }

        if(Double.parseDouble(note) > 100 || Double.parseDouble(note) <0 ){
            this.note.setError("La note doit être entre 0 et 100");
            this.note.requestFocus();
            return;
        }
        if (moyenne.isEmpty()) {
            this.moyenne.setError("Moyenne manquante");
            this.moyenne.requestFocus();
            return;
        }

        if(Double.parseDouble(moyenne) > 100 || Double.parseDouble(moyenne) <0 ){
            this.moyenne.setError("La moyenne doit être entre 0 et 100");
            this.moyenne.requestFocus();
            return;
        }
        if (ecartType.isEmpty()) {
            this.ecartType.setError("Écart type manquant");
            this.ecartType.requestFocus();
            return;
        }

        if(Double.parseDouble(ecartType) > 100 || Double.parseDouble(ecartType) <0 ){
            this.ecartType.setError("L’écart type doit être entre 0 et 100");
            this.ecartType.requestFocus();
            return;
        }
        if (moyenneSecondaire.isEmpty()) {
            this.moyenneSecondaire.setError("Moyenne au secondaire manquant");
            this.moyenneSecondaire.requestFocus();
            return;
        }

        if(Double.parseDouble(moyenneSecondaire) > 100 || Double.parseDouble(moyenneSecondaire) <0 ){
            this.moyenneSecondaire.setError("La moyenne au secondaire doit être entre 0 et 100");
            this.moyenneSecondaire.requestFocus();
            return;
        }
        if (nbrUnites.isEmpty()) {
            this.nbr_unites.setError("Nombre d’unités manquant");
            this.nbr_unites.requestFocus();
            return;
        }

        if(Double.parseDouble(nbrUnites) > 100 || Double.parseDouble(nbrUnites) <0 ){
            this.nbr_unites.setError("Le nombre d’unités doit être entre 0 et 100");
            this.nbr_unites.requestFocus();
            return;
        }


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);

                if(userProfil != null){
                    ArrayList<CoteR> coteRArrayList = userProfil.coteRArraylist;
                    if(coteRArrayList.get(0).matiere.equals("Effacable")){
                        coteRArrayList.remove(0);
                    }
                    coteRArrayList.add(new CoteR(matiere,  Double.parseDouble(note), Double.parseDouble(moyenne), Double.parseDouble(ecartType), Double.parseDouble(moyenneSecondaire), Double.parseDouble(nbrUnites)));
                    reference.child(userID).child("coteRArraylist").setValue(coteRArrayList);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Il y a eu une erreur!", Toast.LENGTH_SHORT).show();
            }
        });

        Fragment fragmentCalcCoteR = new CalculatriceCoteRFragment();
        FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.fragment_container, fragmentCalcCoteR).commit();
        Toast.makeText(getContext(), "Cote R rajoutée!!", Toast.LENGTH_LONG).show();



    }
}

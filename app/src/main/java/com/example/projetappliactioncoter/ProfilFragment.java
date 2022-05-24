package com.example.projetappliactioncoter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {} factory method to
 * create an instance of this fragment.
 */


public class ProfilFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    /**
     * Constructeur
     */
    public ProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Méthode non utilisée pour les fragments
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * Créer le fragment de la page du profil
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil2, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        //Initialisation de toutes les différents objets dans notre fichier .xml
        final TextView nomCompletTextView = (TextView) view.findViewById(R.id.texte_profil_nom_shown);
        final TextView courrielTextView = (TextView) view.findViewById(R.id.texte_profil_email_shown);
        final TextView ageTextView = (TextView) view.findViewById(R.id.texte_profil_age_shown);
        final TextView ecoleTextView = (TextView) view.findViewById(R.id.texte_profil_ecole_shown);
        final TextView programmeTextView = (TextView) view.findViewById(R.id.texte_profil_programme_shown);
        final TextView coteRTextView = (TextView) view.findViewById(R.id.texte_profil_coteR_shown);


        //TODO rajouter un image picker pour l image du profil

        //Entrer dans la branche des users dans le database pour y faire des changements
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);

                //Display les informations du user dans le view
                if(userProfil != null){
                    String nomComplet = userProfil.nomComplet;
                    String courriel = userProfil.email;
                    String age = userProfil.age;
                    String ecole = userProfil.ecole;
                    String programme = userProfil.programme;

                    nomCompletTextView.setText(nomComplet);
                    courrielTextView.setText(courriel);
                    ageTextView.setText(age + " ans");
                    ecoleTextView.setText(ecole);
                    programmeTextView.setText(programme);

                    //Si le user a une arraylist seulement avec la cote r "effacable", montrer qu’il en a pas
                    if(userProfil.coteRArraylist.get(0).matiere.equals("Effacable") && userProfil.coteRArraylist.size() == 1){
                        coteRTextView.setText("Pas de Cote R encore");

                    //Sinon calculer la cote r et la montrer
                    } else {
                        coteRTextView.setText(String.valueOf(CoteR.calculCoteRFinal(userProfil.coteRArraylist)));
                    }





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //TODO ion noe if this works
                Toast.makeText(getContext(), "Il y a eu une erreur!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}
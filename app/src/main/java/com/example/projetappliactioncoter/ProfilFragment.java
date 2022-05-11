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
/*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
*/
    public ProfilFragment() {
        // Required empty public constructor
    }
/*
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.

    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil2, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nomCompletTextView = (TextView) view.findViewById(R.id.texte_profil_nom_shown);
        final TextView courrielTextView = (TextView) view.findViewById(R.id.texte_profil_email_shown);
        final TextView ageTextView = (TextView) view.findViewById(R.id.texte_profil_age_shown);
        final TextView ecoleTextView = (TextView) view.findViewById(R.id.texte_profil_ecole_shown);
        final TextView programmeTextView = (TextView) view.findViewById(R.id.texte_profil_programme_shown);
        final TextView coteRTextView = (TextView) view.findViewById(R.id.texte_profil_coteR_shown);

        //TODO rajouter un progress bar en attendant que les info load in avec un addOnCompleteListner
        //TODO rajouter un image picker pour l image du profil
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);

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

                    if(userProfil.coteRArraylist.get(0).matiere.equals("Effacable") && userProfil.coteRArraylist.size() == 1){
                        coteRTextView.setText("Pas de Cote R encore");
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
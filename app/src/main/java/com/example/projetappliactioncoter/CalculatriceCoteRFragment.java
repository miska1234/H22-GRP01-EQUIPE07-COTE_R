package com.example.projetappliactioncoter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

public class CalculatriceCoteRFragment extends Fragment{

    private ArrayList<CoteRModel> coteRModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button buttonAjouterCoteR;
    FirebaseUser user;
    DatabaseReference reference;
    String userID;

    /**
     * Créer l’activité de la page où l’on peut voir toutes nos cote r
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculatrice_cote_r, container, false);

        //Initialise des variables pour avoir acces à la base de donnée
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        //Initialisation de toutes les différents objets dans notre fichier .xml
        buttonAjouterCoteR = view.findViewById(R.id.ajouterCoteR);
        swipeRefreshLayout = view.findViewById(R.id.swipper_layout);

        //Permet de refresh le fragment
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            /**
             * Méthode qui permet de refresh les cote r en rappelant la méthode setUpCoteRModels(view)
             */
            @Override
            public void onRefresh() {
                setUpCoteRModels(view);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //Onclick
        buttonAjouterCoteR.setOnClickListener(new View.OnClickListener() {
            /**
             * Méthode permettant de change du fragment où l’on peut voir
             * les cote r au fragment où l’on peut les calculer
             * @param view
             */
            @Override
            public void onClick(View view) {

                //Changement de fragment
                Fragment fragmentRealCalcCoteR = new CalculatriceCoteR_realCalcul_Fragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, fragmentRealCalcCoteR).commit();
            }
        });


        //Méthode pour setup le model de cote r que le recyclerview va utiliser
        setUpCoteRModels(view);

        return view;
    }

    /**
     * Méthode pour setup le model de cote r que le recyclerview va utiliser
     * @param view
     */
    private void setUpCoteRModels(View view){

        //Acces à la base de donnée de l’utilisateur
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            /**
             * Méthode overided qui permet de changer les valeurs dans la database
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);



                if(userProfil != null){
                    //Initialliser une nouvelle arraylist avec celle dans la database du user
                    ArrayList<CoteR> coteRArrayList = userProfil.coteRArraylist;

                    //Si le size de la array list de cote r est différent de 1
                    if(coteRArrayList.size() != 1){

                            //changer le array list du recycler view qui va contenir toutes les cote r dans le data base
                            // (Donc c’est comme si on copier le arraylist dans la data base pour la montrer)
                            coteRModels.clear();
                            for (int i = 1; i < coteRArrayList.size(); i++) {
                                coteRModels.add(new CoteRModel(coteRArrayList.get(i).matiere,
                                        String.valueOf(CoteR.calculCoteR(coteRArrayList.get(i).note, coteRArrayList.get(i).moyenne,
                                                coteRArrayList.get(i).ecartType, coteRArrayList.get(i).moyenneAuSecondaire))));
                            }

                    }

                }

                //Donner au recycler view la arraylist qu’il a besoin de montrer
                CoteR_RecyclerViewAdapter coteR_recyclerViewAdapter = new CoteR_RecyclerViewAdapter(getContext(), coteRModels);
                RecyclerView recyclerView = view.findViewById(R.id.recyclerview_cote_r);
                recyclerView.setAdapter(coteR_recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                //Appele de l’interface setOnItemListener pour pouvoir utiliser ses méthodes
                coteR_recyclerViewAdapter.setOnItemListener(new CoteR_RecyclerViewAdapter.OnItemClickListener() {
                    /**
                     * Méthode qui permet de edit la cote r en appuyant sur le petit crayon
                     * @param position
                     */
                    @Override
                    public void onEditClick(int position) {
                        //Change de fragment au fragment du changement de cote r
                        Fragment fragmentModifierCoteR = new Modifier_coteR(position);
                        FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                        fm.replace(R.id.fragment_container, fragmentModifierCoteR).commit();
                    }

                    /**
                     * Méthode qui permet de delete une cote r du view et de la data base
                     * @param position
                     */
                    @Override
                    public void onDeleteClick(int position) {
                        //Delete la cote r du view (recycler view)
                        coteRModels.remove(position);
                        coteR_recyclerViewAdapter.notifyItemRemoved(position);

                        //Delete la cote r du database de l’utilisateur
                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            /**
                             * Méthode overided qui permet de changer les valeurs dans la database
                             * @param snapshot
                             */
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);

                                if(userProfil != null){
                                    //Enlève la cote r dans la arraylist dans la database
                                    ArrayList<CoteR> coteRArrayList = userProfil.coteRArraylist;
                                    coteRArrayList.remove(position + 1);

                                    //Reset la array list
                                    reference.child(userID).child("coteRArraylist").setValue(coteRArrayList);
                                }

                            }
                            /**
                             * Méthode permettant de gérer les erreurs en lien avec la database
                             * @param error
                             */
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getContext(), "Il y a eu une erreur!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }

            /**
             * Méthode permettant de gérer les erreurs en lien avec la database
             * @param error
             */
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Il y a eu une erreur!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

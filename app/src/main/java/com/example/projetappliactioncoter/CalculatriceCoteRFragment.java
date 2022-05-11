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

    ArrayList<CoteRModel> coteRModels = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    Button buttonAjouterCoteR;
    FirebaseUser user;
    DatabaseReference reference;
    String userID;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculatrice_cote_r, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        buttonAjouterCoteR = view.findViewById(R.id.ajouterCoteR);
        swipeRefreshLayout = view.findViewById(R.id.swipper_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpCoteRModels(view);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        buttonAjouterCoteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentRealCalcCoteR = new CalculatriceCoteR_realCalcul_Fragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, fragmentRealCalcCoteR).commit();
            }
        });


        setUpCoteRModels(view);
        //TODO Le view change pas et update pas automatiquement apres avoir ajouter le bhy pcq ca prend du temps avant que ca change sur firebase
        //TODO effacer les bhy et edit



        return view;
    }

    private void setUpCoteRModels(View view){

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);



                if(userProfil != null){
                    ArrayList<CoteR> coteRArrayList = userProfil.coteRArraylist;

                    if(coteRArrayList.size() != 1){


                            coteRModels.clear();
                            for (int i = 1; i < coteRArrayList.size(); i++) {
                                coteRModels.add(new CoteRModel(coteRArrayList.get(i).matiere,
                                        String.valueOf(CoteR.calculCoteR(coteRArrayList.get(i).note, coteRArrayList.get(i).moyenne,
                                                coteRArrayList.get(i).ecartType, coteRArrayList.get(i).moyenneAuSecondaire))));
                            }

                    }

                }

                //TODO il y a une erreur : E/RecyclerView: No adapter attached; skipping layout -> verify c koi l erreur
                //TODO c la k il y a les on Edit Click
                CoteR_RecyclerViewAdapter coteR_recyclerViewAdapter = new CoteR_RecyclerViewAdapter(getContext(), coteRModels);
                RecyclerView recyclerView = view.findViewById(R.id.recyclerview_cote_r);
                recyclerView.setAdapter(coteR_recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                coteR_recyclerViewAdapter.setOnItemListener(new CoteR_RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onEditClick(int position) {
                        Fragment fragmentModifierCoteR = new Modifier_coteR(position);
                        FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                        fm.replace(R.id.fragment_container, fragmentModifierCoteR).commit();
                    }

                    @Override
                    public void onDeleteClick(int position) {
                        coteRModels.remove(position);
                        coteR_recyclerViewAdapter.notifyItemRemoved(position);
                        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Utilisateur userProfil = snapshot.getValue(Utilisateur.class);

                                if(userProfil != null){
                                    ArrayList<CoteR> coteRArrayList = userProfil.coteRArraylist;
                                    coteRArrayList.remove(position + 1);

                                    reference.child(userID).child("coteRArraylist").setValue(coteRArrayList);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getContext(), "Il y a eu une erreur!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Il y a eu une erreur!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

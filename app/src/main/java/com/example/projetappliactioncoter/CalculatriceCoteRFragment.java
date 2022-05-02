package com.example.projetappliactioncoter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CalculatriceCoteRFragment extends Fragment{

    ArrayList<CoteRModel> coteRModels = new ArrayList<>();
    Button buttonAjouterCoteR;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculatrice_cote_r, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_cote_r);
        buttonAjouterCoteR = view.findViewById(R.id.ajouterCoteR);
        buttonAjouterCoteR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentRealCalcCoteR = new CalculatriceCoteR_realCalcul_Fragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, fragmentRealCalcCoteR).commit();
            }
        });

        setUpCoteRModels();
        CoteR_RecyclerViewAdapter coteR_recyclerViewAdapter = new CoteR_RecyclerViewAdapter(getContext(), coteRModels);

        recyclerView.setAdapter(coteR_recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        coteR_recyclerViewAdapter.setOnItemListener(new CoteR_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                coteRModels.get(position).setCoteR("Bot");
                coteR_recyclerViewAdapter.notifyItemChanged(position);
            }

            @Override
            public void onDeleteClick(int position) {
                coteRModels.remove(position);
                coteR_recyclerViewAdapter.notifyItemRemoved(position);
            }
        });

        return view;
    }

    private void setUpCoteRModels(){
        String[] matiereNom = getResources().getStringArray(R.array.coursTest);
        String[] coteR = getResources().getStringArray(R.array.Cote_r);

        for(int i = 0 ;  i < matiereNom.length; i++){
            coteRModels.add(new CoteRModel(matiereNom[i], coteR[i]));
        }
    }

}

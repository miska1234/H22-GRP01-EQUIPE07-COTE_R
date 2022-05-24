package com.example.projetappliactioncoter;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UniversiteFragment extends Fragment implements View.OnClickListener{

    private CardView cardViewUdem;
    private CardView cardViewPoly;
    private CardView cardViewULaval;
    private CardView cardViewSherbrook;
    private CardView cardViewConcordia;
    private CardView cardViewMcgill;


    /**
     * Créer le fragment qui montre les universités
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_universitez, container, false);

        //Initialisation de toutes les différents objets dans notre fichier .xml
        cardViewUdem = view.findViewById(R.id.cardViewUdem);
        cardViewPoly = view.findViewById(R.id.cardViewPoly);
        cardViewULaval = view.findViewById(R.id.cardViewUlaval);
        cardViewSherbrook = view.findViewById(R.id.cardViewUsherbrook);
        cardViewConcordia = view.findViewById(R.id.cardViewConcordia);
        cardViewMcgill = view.findViewById(R.id.cardViewMcgill);

        //Onclick pour toutes les cardviews, ainsi pour toutes les universitees
        cardViewUdem.setOnClickListener(this);
        cardViewPoly.setOnClickListener(this);
        cardViewULaval.setOnClickListener(this);
        cardViewSherbrook.setOnClickListener(this);
        cardViewConcordia.setOnClickListener(this);
        cardViewMcgill.setOnClickListener(this);

        return view;
    }

    /**
     * Méthode qui permet de changer de fragment selon le cardview qu’on à clicker
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            //Changer le fragment pour celui des cote r de UdeM
            case R.id.cardViewUdem:
                Fragment fragmentUdem = new UniversiteUdemFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, fragmentUdem).commit();
                break;

            //Changer le fragment pour celui des cote r de Poly
            case R.id.cardViewPoly:
                Fragment fragmentPoly = new UniversitePolyFragment();
                FragmentTransaction fmPoly = getActivity().getSupportFragmentManager().beginTransaction();
                fmPoly.replace(R.id.fragment_container, fragmentPoly).commit();
                break;

            //Changer le fragment pour celui des cote r de ULaval
            case R.id.cardViewUlaval:
                Fragment fragmentULaval = new UniversiteULavalFragment();
                FragmentTransaction fmULaval = getActivity().getSupportFragmentManager().beginTransaction();
                fmULaval.replace(R.id.fragment_container, fragmentULaval).commit();
                break;

            //Changer le fragment pour celui des cote r de USherbrook
            case R.id.cardViewUsherbrook:
                Fragment fragmentUsherbrook = new UniversiteUSherbrookFragment();
                FragmentTransaction fmusher = getActivity().getSupportFragmentManager().beginTransaction();
                fmusher.replace(R.id.fragment_container, fragmentUsherbrook).commit();
                break;

            //Changer le fragment pour celui des cote r de Concordia
            case R.id.cardViewConcordia:
                Fragment fragmentConcordia = new UniversiteConcordiaFragment();
                FragmentTransaction fmConc = getActivity().getSupportFragmentManager().beginTransaction();
                fmConc.replace(R.id.fragment_container, fragmentConcordia).commit();
                break;

            //Changer le fragment pour celui des cote r de McGill
            case R.id.cardViewMcgill:
                Fragment fragmentMcgill = new UniversiteMcgillFragment();
                FragmentTransaction fmMcgill = getActivity().getSupportFragmentManager().beginTransaction();
                fmMcgill.replace(R.id.fragment_container, fragmentMcgill).commit();
                break;
        }
    }
}

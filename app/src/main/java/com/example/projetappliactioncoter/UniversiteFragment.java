package com.example.projetappliactioncoter;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UniversiteFragment extends Fragment implements View.OnClickListener{

    CardView cardViewUdem;
    CardView cardViewPoly;
    CardView cardViewULaval;
    CardView cardViewSherbrook;
    CardView cardViewConcordia;
    CardView cardViewMcgill;

            //montreal, poly, laval, sher, condordia, mcgill

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_universitez, container, false);

        cardViewUdem = view.findViewById(R.id.cardViewUdem);
        cardViewPoly = view.findViewById(R.id.cardViewPoly);
        cardViewULaval = view.findViewById(R.id.cardViewUlaval);
        cardViewSherbrook = view.findViewById(R.id.cardViewUsherbrook);
        cardViewConcordia = view.findViewById(R.id.cardViewConcordia);
        cardViewMcgill = view.findViewById(R.id.cardViewMcgill);

        cardViewUdem.setOnClickListener(this);
        cardViewPoly.setOnClickListener(this);
        cardViewULaval.setOnClickListener(this);
        cardViewSherbrook.setOnClickListener(this);
        cardViewConcordia.setOnClickListener(this);
        cardViewMcgill.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.cardViewUdem:
                Fragment fragmentUdem = new UniversiteUdemFragment();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, fragmentUdem).commit();
                break;
            case R.id.cardViewPoly:
                Fragment fragmentPoly = new UniversitePolyFragment();
                FragmentTransaction fmPoly = getActivity().getSupportFragmentManager().beginTransaction();
                fmPoly.replace(R.id.fragment_container, fragmentPoly).commit();
                break;
            case R.id.cardViewUlaval:
                Fragment fragmentULaval = new UniversiteULavalFragment();
                FragmentTransaction fmULaval = getActivity().getSupportFragmentManager().beginTransaction();
                fmULaval.replace(R.id.fragment_container, fragmentULaval).commit();
                break;
            case R.id.cardViewUsherbrook:
                Fragment fragmentUsherbrook = new UniversiteUSherbrookFragment();
                FragmentTransaction fmusher = getActivity().getSupportFragmentManager().beginTransaction();
                fmusher.replace(R.id.fragment_container, fragmentUsherbrook).commit();
                break;
            case R.id.cardViewConcordia:
                Fragment fragmentConcordia = new UniversiteConcordiaFragment();
                FragmentTransaction fmConc = getActivity().getSupportFragmentManager().beginTransaction();
                fmConc.replace(R.id.fragment_container, fragmentConcordia).commit();
                break;
            case R.id.cardViewMcgill:
                Fragment fragmentMcgill = new UniversiteMcgillFragment();
                FragmentTransaction fmMcgill = getActivity().getSupportFragmentManager().beginTransaction();
                fmMcgill.replace(R.id.fragment_container, fragmentMcgill).commit();
                break;
        }
    }
}

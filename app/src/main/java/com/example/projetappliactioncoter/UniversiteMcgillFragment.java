package com.example.projetappliactioncoter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class UniversiteMcgillFragment extends Fragment {

    /**
     * Créer le fragment pour les cote r à l’université de McGill
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_universite_mcgill, container, false);
    }
}

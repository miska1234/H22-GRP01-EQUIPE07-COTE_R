package com.example.projetappliactioncoter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InformationCoteRFragment extends Fragment{

    /**
     * Créer le fragment de la page de l’information sur la cote r
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_information_cote_r, container, false);
    }
}

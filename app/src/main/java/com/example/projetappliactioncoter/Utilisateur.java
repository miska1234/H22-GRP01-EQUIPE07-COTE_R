package com.example.projetappliactioncoter;

import java.util.ArrayList;

public class Utilisateur {

    public String nomComplet, age, ecole, programme, email;
    public ArrayList<CoteR> coteRArraylist;

    /**
     * Constructeur de base
     */
    public Utilisateur(){

    }

    /**
     * Constructeur qui prend toutes l’information sur l’utilisateur
     * @param nomComplet
     * @param age
     * @param ecole
     * @param programme
     * @param email
     * @param coteRArraylist
     */
    public Utilisateur(String nomComplet, String age,String ecole,String programme,String email, ArrayList<CoteR> coteRArraylist ){
        this.nomComplet = nomComplet;
        this.age = age;
        this.ecole = ecole;
        this.programme = programme;
        this.email = email;
        this.coteRArraylist = coteRArraylist;
    }
}

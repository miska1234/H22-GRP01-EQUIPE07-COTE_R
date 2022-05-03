package com.example.projetappliactioncoter;

import java.util.ArrayList;

public class Utilisateur {

    public String nomComplet, age, ecole, programme, email;
    public ArrayList<CoteR> coteRArraylist;

    public Utilisateur(){

    }

    public Utilisateur(String nomComplet, String age,String ecole,String programme,String email, ArrayList<CoteR> coteRArraylist ){
        this.nomComplet = nomComplet;
        this.age = age;
        this.ecole = ecole;
        this.programme = programme;
        this.email = email;
        this.coteRArraylist = coteRArraylist;
    }
}

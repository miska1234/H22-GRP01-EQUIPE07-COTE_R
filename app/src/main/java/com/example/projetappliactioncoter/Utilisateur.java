package com.example.projetappliactioncoter;

public class Utilisateur {

    public String nomComplet, age, ecole, programme, email;

    public Utilisateur(){

    }

    public Utilisateur(String nomComplet, String age,String ecole,String programme,String email){
        this.nomComplet = nomComplet;
        this.age = age;
        this.ecole = ecole;
        this.programme = programme;
        this.email = email;
    }
}

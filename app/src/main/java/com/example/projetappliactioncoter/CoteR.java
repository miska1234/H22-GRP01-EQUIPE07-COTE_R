package com.example.projetappliactioncoter;

import java.util.ArrayList;

public class CoteR {

    public String matiere;
    public double note, moyenne, ecartType, moyenneAuSecondaire, nbrUnite;

    public CoteR(){

    }

    public CoteR(String matiere, double note, double moyenne, double ecartType, double moyenneAuSecondaire, double nbrUnite) {
        this.matiere = matiere;
        this.note = note;
        this.moyenne = moyenne;
        this.ecartType = ecartType;
        this.moyenneAuSecondaire = moyenneAuSecondaire;
        this.nbrUnite = nbrUnite;
    }

    public static double calculCoteR(double note, double moyenne, double ecartType, double moyenneAuSecondaire){
        double Z = (note-moyenne)/ecartType;
        double IFG = (moyenneAuSecondaire-75)/14;
        return (double)(Math.round((Z+IFG+5)*5*1000)/1000.0);
    }

    public static double calculCoteRFinal(ArrayList<CoteR> coteRArrayList){
        double coteRFinal = 0;
        double nbrUnitestotal = 0;
        double cpt1 = 0;
        double cpt2 = 0;

        for(CoteR coteR : coteRArrayList){
            if(cpt1 != 0){
                nbrUnitestotal+= coteR.nbrUnite;
            }
            cpt1++;
        }

        for(CoteR coteR : coteRArrayList){
            if(cpt2 != 0) {
                coteRFinal += ((coteR.nbrUnite / nbrUnitestotal) * calculCoteR(coteR.note, coteR.moyenne, coteR.ecartType, coteR.moyenneAuSecondaire));
            }
            cpt2++;
        }


        return  (double)(Math.round(coteRFinal*1000)/1000.0);
    }

}

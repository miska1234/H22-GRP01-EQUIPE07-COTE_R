package com.example.projetappliactioncoter;

import java.util.ArrayList;

public class CoteR {

    public String matiere;
    public double note, moyenne, ecartType, moyenneAuSecondaire, nbrUnite;

    /**
     * Conctructeur de base
     */
    public CoteR(){

    }

    /**
     * Constructeur qui prend toutes les informations nécessaires pour calculer la cote r
     * @param matiere
     * @param note
     * @param moyenne
     * @param ecartType
     * @param moyenneAuSecondaire
     * @param nbrUnite
     */
    public CoteR(String matiere, double note, double moyenne, double ecartType, double moyenneAuSecondaire, double nbrUnite) {
        this.matiere = matiere;
        this.note = note;
        this.moyenne = moyenne;
        this.ecartType = ecartType;
        this.moyenneAuSecondaire = moyenneAuSecondaire;
        this.nbrUnite = nbrUnite;
    }

    /**
     * Calcul d’une seule cote r pour un cours
     * @param note
     * @param moyenne
     * @param ecartType
     * @param moyenneAuSecondaire
     * @return
     */
    public static double calculCoteR(double note, double moyenne, double ecartType, double moyenneAuSecondaire){
        double Z = (note-moyenne)/ecartType;
        double IFG = (moyenneAuSecondaire-75)/14;
        return (double)(Math.round((Z+IFG+5)*5*1000)/1000.0);
    }

    /**
     * Calcul de la moyenne des cote r
     * @param coteRArrayList
     * @return
     */
    public static double calculCoteRFinal(ArrayList<CoteR> coteRArrayList){
        double coteRFinal = 0;
        double nbrUnitestotal = 0;
        double cpt1 = 0;
        double cpt2 = 0;

        //Calculer le nombre d’unités totals de toutes les cours de l’utilisateur
        //(Passer le premier cours parce que ce n’est pas un vrai, celui-ci a juste était mis pour ne pas perdre le arraylist dans le firebase si celui-ci est vide)
        for(CoteR coteR : coteRArrayList){
            if(cpt1 != 0){
                nbrUnitestotal+= coteR.nbrUnite;
            }
            cpt1++;
        }

        //Calcul de la moyenne ponderée
        for(CoteR coteR : coteRArrayList){
            if(cpt2 != 0) {
                coteRFinal += ((coteR.nbrUnite / nbrUnitestotal) * calculCoteR(coteR.note, coteR.moyenne, coteR.ecartType, coteR.moyenneAuSecondaire));
            }
            cpt2++;
        }


        return  (double)(Math.round(coteRFinal*1000)/1000.0);
    }

}

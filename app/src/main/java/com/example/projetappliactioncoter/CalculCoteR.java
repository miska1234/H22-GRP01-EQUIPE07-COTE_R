package com.example.projetappliactioncoter;


import java.util.Scanner;

public class CalculCoteR {

    public static void main(String[] args) {

        double cotez = cotez();
        double ifgz = ifgz();
        System.out.println("Voici votre Cote R approximative : " + coter(ifgz, cotez));
    }
    public static double coter(double ifgz, double cotez){
        double coter;
        coter = (cotez + ifgz + 5) * 5;

        return coter;
    }

    public static double cotez(){
        Scanner sc = new Scanner(System.in);
        Scanner scD = new Scanner(System.in);
        double cotez ;
        int note = 0;
        boolean notevalide = false;
        double ecartype = 0;
        boolean ecartypevalide = false;
        int moyenne = 0;
        boolean moyennevalide = false;

        while (!notevalide){
            System.out.println("Quelle est votre note en % (entre 0 et 100) : ");
            note = sc.nextInt();
            if (note <= 100 && note >= 0)
                notevalide = true;
        }

        while (!moyennevalide){
            System.out.println("Quelle est le moyenne du groupe en % (entre 0 et 100) : ");
            moyenne = sc.nextInt();
            if (moyenne <= 100 && moyenne >= 0)
                moyennevalide = true;
        }
        while (!ecartypevalide){
            System.out.println("Quelle est l'écart type du groupe en % (entre 0 et 100) : ");
            ecartype = scD.nextDouble();
            if (ecartype <= 100 && ecartype >= 0)
                ecartypevalide = true;
        }

        cotez = (note - moyenne)/ecartype;

        return cotez;
    }

    public static double ifgz(){
        Scanner sc = new Scanner(System.in);
        double ifgz ;
        double moyenne = 0;
        boolean moyennevalide = false;

        while (!moyennevalide){
            System.out.println("Quelle était votre la moyenne de votre groupe au secondaire en % (** mettez 75 si vous ne savez pas **)");
            moyenne = sc.nextDouble();
            if (moyenne <= 100 && moyenne >= 0)
                moyennevalide = true;
        }
        ifgz = (moyenne - 75) / 14;
        return ifgz;
    }


}




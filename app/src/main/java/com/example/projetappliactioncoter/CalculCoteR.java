package com.example.projetappliactioncoter;



import java.util.Scanner;

    public class CalculCoteR {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            double note = note(sc);
            double moyenne = moyenne(sc);
            int cotez = cotez(sc);
            int ifgz = ifgz(sc);
            int idgz = idgz(sc);
            float coter = calculfinal(ifgz, idgz, cotez);
            System.out.println("Cote R finale: " + coter);
        }
        public static float calculfinal(int ifgz, int idgz, int cotez){
            float coter = 0;
            coter = (cotez * idgz + ifgz) * 5;

            return coter;
        }

        public static double ajouter( double unitecour, double unitetotal, int nbdecours){
            double w = unitecour/unitetotal;
            return w;
        }
        public static double unite(Scanner sc){
            double unite = -100;
            boolean valid = false;
            while (!valid){
                System.out.println("Quelle est votre note?");
                unite = sc.nextDouble();
                if (unite >= 0 && unite <= 3){
                    valid = true;
                }
            }
            return unite;
        }

        public static double note(Scanner sc){
            double note = -100;
            boolean valid = false;
            while (!valid){
                System.out.println("Quelle est votre note?");
                note = sc.nextDouble();
                if (note >= 0 && note <= 100){
                    valid = true;
                }
            }
            return note;
        }
        public static double moyenne(Scanner sc){
            double moyenne = -100;
            boolean valid = false;
            while (!valid){
                System.out.println("Quelle est la moyenne de votre groupe?");
                moyenne = sc.nextDouble();
                if (moyenne >= 0 && moyenne <= 100){
                    valid = true;
                }
            }
            return moyenne;
        }
        public static int cotez(Scanner sc){

            //cote z de -4 à 4
            boolean valid = false;
            int cotez = -100;
            while (!valid){
                System.out.println("Choisissez une cote z partant de -4 (très mauvais) à 4 (très bien):");
                cotez = sc.nextInt();
                if (cotez <= 4 && cotez >= -4)
                    valid = true;
            }
            return cotez;
        }
        public static int ifgz(Scanner sc){

            //ifgz de -3 à 3
            boolean valid = false;
            int ifgz = -100;
            while (!valid){
                System.out.println("Choisissez un indice de force de groupe (IFGZ) partant de -3 (très mauvais) à 3 (très bien):");
                ifgz = sc.nextInt();
                if (ifgz <= 3 && ifgz >= -3)
                    valid = true;
            }
            return ifgz;
        }
        public static int idgz(Scanner sc){

            //idgz de -3 à 3
            boolean valid = false;
            int idgz = -100;
            while (!valid){
                System.out.println("Choisissez un écart type de la cote z partant de -3 (très mauvais) à 3 (très bien):");
                idgz = sc.nextInt();
                if (idgz <= 3 && idgz >= -3)
                    valid = true;
            }
            return idgz;
        }


    }


package com.example.projetappliactioncoter;

public class CoteRModel {
    String matière;
    String coteR;

    public CoteRModel(String matière, String coteR) {
        this.matière = matière;
        this.coteR = coteR;
    }

    public CoteRModel(){}

    public String getMatière() {
        return matière;
    }

    public String getCoteR() {
        return coteR;
    }

    public void setMatière(String matière) {
        this.matière = matière;
    }

    public void setCoteR(String coteR) {
        this.coteR = coteR;
    }
}

package com.example.projetappliactioncoter;

public class LangueModelClass {

    private String nomPays;
    private int drapeauImage;

    public LangueModelClass(String nomPays, int drapeauImage) {
        this.nomPays = nomPays;
        this.drapeauImage = drapeauImage;
    }

    public String getNomPays() {
        return nomPays;
    }

    public int getDrapeauImage() {
        return drapeauImage;
    }
}

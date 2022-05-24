package com.example.projetappliactioncoter;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    private Context context;

    /**
     * Constructeur qui prend le contexte en paramètre
     * @param ctx
     */
    public LanguageManager(Context ctx){
        context = ctx;
    }

    /**
     * Méthode qui update la langue dans laquelle l’application est écrite
     * @param code
     */
    public void updateResource(String code){
        Locale locale = new Locale(code);
        locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());


    }

}

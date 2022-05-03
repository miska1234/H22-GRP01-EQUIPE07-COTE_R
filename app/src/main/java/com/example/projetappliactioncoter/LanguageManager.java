package com.example.projetappliactioncoter;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.widget.Button;

import java.util.Locale;

public class LanguageManager {
    private Context ct;

    public LanguageManager(Context ctx)
    {
        ct = ctx;
    }

    public void updateResource(String code)
    {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resource = ct.getResources();
        Configuration configuration = resource.getConfiguration();
        configuration.locale = locale;
        resource.updateConfiguration(configuration, resource.getDisplayMetrics());
    }

}

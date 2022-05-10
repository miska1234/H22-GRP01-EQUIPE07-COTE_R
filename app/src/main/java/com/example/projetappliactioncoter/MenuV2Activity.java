package com.example.projetappliactioncoter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;


public class MenuV2Activity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageView logoutTextView;
    private Button boutonLangueFrancais, boutonLangueAnglais;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_v2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        logoutTextView = (ImageView) findViewById(R.id.logoutButton);

        LanguageManager lang = new LanguageManager(this);
        boutonLangueFrancais = findViewById(R.id.francais);
        boutonLangueAnglais = findViewById(R.id.anglais);


        boutonLangueAnglais.setOnClickListener(view -> {
            lang.updateResource("en");
            recreate();
        });
        boutonLangueFrancais.setOnClickListener(view -> {
            lang.updateResource("fr");
            recreate();
        });


        //TODO setOnClickListener pour le menubutton
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuV2Activity.this, AcceuilleActivity.class));
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.profil_view);
        navigationViewItemSelect();


    }


    public void navigationViewItemSelect() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.echange_livre:
                        fragment = new EchangeLivreFragment();
                        break;
                    case R.id.cote_r_logo:
                        fragment = new CalculatriceCoteRFragment();
                        break;
                    case R.id.profil_view:
                        fragment = new ProfilFragment();
                        break;
                    case R.id.universite:
                        fragment = new UniversiteFragment();
                        break;
                    case R.id.timer:
                        fragment = new PomodoroFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });
    }


}
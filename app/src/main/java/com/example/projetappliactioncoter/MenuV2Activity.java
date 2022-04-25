package com.example.projetappliactioncoter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MenuV2Activity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_v2);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.profil_view);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch(item.getItemId()){
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
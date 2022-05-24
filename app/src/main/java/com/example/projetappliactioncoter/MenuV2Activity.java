package com.example.projetappliactioncoter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MenuV2Activity extends AppCompatActivity  {

    private BottomNavigationView bottomNavigationView;
    private ImageView logoutTextView;

    private ArrayList<LangueModelClass> langueList;
    private LangueAdapter langueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_v2);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        logoutTextView = (ImageView) findViewById(R.id.logoutButton);

        //SETTING ADDAPTERZ
        initialiserList();
        Spinner spinnerLangue = findViewById(R.id.spinner_langue);

        langueAdapter = new LangueAdapter(this, langueList);
        spinnerLangue.setAdapter(langueAdapter);

        LanguageManager languageManager = new LanguageManager(this);




        spinnerLangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LangueModelClass language = (LangueModelClass) adapterView.getItemAtPosition(i);


                switch (i){
                    case 0:
                        break;
                    case 1:
                        languageManager.updateResource("fr");

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                        break;
                    case 2:
                        languageManager.updateResource("en");

                        Intent intent2 = getIntent();
                        finish();
                        startActivity(intent2);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

    public void initialiserList(){
        langueList = new ArrayList<>();
        langueList.add(new LangueModelClass("", R.drawable.ic_menu));
        langueList.add(new LangueModelClass("Français", R.drawable.ic_france_flag));
        langueList.add(new LangueModelClass("English", R.drawable.ic_angleterre_flag));
    }

    public void navigationViewItemSelect(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch(item.getItemId()){
                    case R.id.echange_livre:
                        fragment = new InformationCoteRFragment();
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
                        fragment = new EncouragementFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });
    }




}
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

    /**
     * Créer l’activité de la page du menu
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_v2);

        //Initialisation de toutes les différents objets dans notre fichier .xml
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        logoutTextView = (ImageView) findViewById(R.id.logoutButton);

        //Initialiser la liste pour le spinner de la langue
        initialiserList();
        Spinner spinnerLangue = findViewById(R.id.spinner_langue);

        langueAdapter = new LangueAdapter(this, langueList);
        spinnerLangue.setAdapter(langueAdapter);

        LanguageManager languageManager = new LanguageManager(this);



        //Si l’on appuie sur le spinner
        spinnerLangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Méthode qui regarde le item qui est selected dans le spinner
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LangueModelClass language = (LangueModelClass) adapterView.getItemAtPosition(i);



                switch (i){
                    //Le premier cas, rien faire (C’est un dummy case pour éviter une loop infinie de refresh
                    case 0:
                        break;

                    //Le deuxième cas change la langue en français et refresh l’activité
                    case 1:
                        languageManager.updateResource("fr");

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                        break;

                    //Le troisième cas change la langue en anglais et refresh l’activité
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
                //Sert a rien
            }
        });


        //Le boutton logout permet de logout l’utilisateur
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Méthode de firebase qui logout le user
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuV2Activity.this, AcceuilleActivity.class));
            }
        });

        //Set le premier fragment comme étant celui du profil
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfilFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.profil_view);
        navigationViewItemSelect();



    }

    /**
     * Initialiser liste des langues
     */
    public void initialiserList(){
        langueList = new ArrayList<>();
        langueList.add(new LangueModelClass("", R.drawable.ic_menu));
        langueList.add(new LangueModelClass("Français", R.drawable.ic_france_flag));
        langueList.add(new LangueModelClass("English", R.drawable.ic_angleterre_flag));
    }

    /**
     * Méthode qui permet de naviguer entre les fragments grâce à une barre de navigation
     */
    public void navigationViewItemSelect(){
        //Si l’on click sur un des icones de la barre de navigation, le fragment change selon l’icon
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                //Permet de switch les fragments en lien avec leur icon
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

                //Switch fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
            }
        });
    }




}
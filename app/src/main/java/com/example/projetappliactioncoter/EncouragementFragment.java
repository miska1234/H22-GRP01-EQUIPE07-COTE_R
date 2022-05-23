package com.example.projetappliactioncoter;


import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class EncouragementFragment extends Fragment {

    LinearLayout linearLayoutEncouragement;
    TextView textViewEncouragement;
    ArrayList<String> listeDeCitation;

    public EncouragementFragment(){
        setListeDeCitation();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_encouragement, container, false);
        linearLayoutEncouragement = view.findViewById(R.id.layout_motivation_click);
        textViewEncouragement = view.findViewById(R.id.texte_encouragement);

        linearLayoutEncouragement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerDeCitationEncouragement();
            }
        });


        return view;
    }

    public void setListeDeCitation(){
        listeDeCitation = new ArrayList<>();
        listeDeCitation.add("'Dans le domaine des idées, tout dépend de l’enthousiasme. " +
                "Dans le monde réel, tout repose sur la persévérance.'\n\n -Johann Wolfgang von Goethe");
        listeDeCitation.add("'Le succès, c’est se promener d’échecs en échecs tout en restant motivé.'\n\n -Winston Churchill");
        listeDeCitation.add("'Le seul endroit où le succès vient avant le travail, c’est dans le dictionnaire.'\n\n -Vidal Sassoon");
        listeDeCitation.add("'Dites à tout le monde ce que vous voulez faire et quelqu’un vous aidera à l’accomplir.'\n\n -W. Clement Stone");
        listeDeCitation.add("'Peu importe qui vous êtes ou qui vous avez été, vous pouvez être qui vous voulez.'\n\n -W. Clement Stone");
        listeDeCitation.add("'Dans 20 ans, tu seras plus déçu par les choses que tu n’auras pas faites que par celles que tu auras accomplies. " +
                "Alors, largue les amarres, sors du port, attrape les alizés par les voiles. Explore. Rêve. Découvre.'\n\n -Mark Twain");
        listeDeCitation.add("'Commencez maintenant, pas demain. Demain est une excuse de perdant.'\n\n -Andrew Fashion");
        listeDeCitation.add("'Les coups de circuit d’hier ne font pas gagner les parties d’aujourd’hui.'\n\n -Babe Ruth");
        listeDeCitation.add("'Je n’ai pas échoué. J’ai simplement trouvé 10 000 façons ne pas y arriver.'\n\n -Thomas Edison");
        listeDeCitation.add("'Vous devez soit modifier vos rêves ou améliorer vos compétences.'\n\n -Jim Rohn");

        listeDeCitation.add("'Le meilleur moyen de prévoir le future, c’est de le créer.'\n\n -Peter Drucker");
        listeDeCitation.add("'Tenez-vous loin des gens qui tentent de diminuer vos ambitions. Les « petites » personnes font toujours cela, mais les « grandes », " +
                "elles, vous font sentir que vous pouvez également devenir génial.'\n\n -Mark Twain");
        listeDeCitation.add("'Chaque bonne réalisation, grande ou petite, connait ses périodes de corvées et de triomphes; un début, un combat et une victoire.'\n\n -Mahatma Gandhi");
        listeDeCitation.add("'Pas une seule personne dont on se rappelle leurs exploits et leurs accomplissements ont vécu une vie facile.'\n\n -Ryan P. Allis");
        listeDeCitation.add("'La chose la plus précieuse que vous pouvez faire est une erreur. Vous ne pouvez rien apprendre en étant parfait.'\n\n -Adam Osborne");
        listeDeCitation.add("'La logique vous mènera de A à B. L’imagination vous mènera partout.'\n\n -Albert Einstein");
        listeDeCitation.add("'Si tout est sous contrôle, vous n’allez pas assez vite.'\n\n -Mario Andretti");
        listeDeCitation.add("'Prenez vos décisions en fonction d’où vous allez, pas en fonction d’où vous êtes.'\n\n -James Arthur Ray");
        listeDeCitation.add("'La valeur d’un homme n’est pas supérieure à la valeur de ses ambitions.'\n\n -Marcus Aurelius Antoninus");
        listeDeCitation.add("'Si vous ne pouvez pas faire de grandes choses, faites de petites choses de façon grandioses.'\n\n -Napoleon Hill");

        listeDeCitation.add("'La pensée positive vous permettra de tout faire d’une meilleure façon que la pensée négative.'\n\n -Zig Ziglar");
        listeDeCitation.add("'Je peux accepter l’échec, tout le monde échoue dans quelque chose. Mais je ne peux accepter de ne pas essayer.'\n\n -Michael Jordan");
    }

    public void changerDeCitationEncouragement(){
        int max = listeDeCitation.size();
        textViewEncouragement.setText(listeDeCitation.get((int)(Math.random()*max - 1)));
    }


}

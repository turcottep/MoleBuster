package com.example.philippe.molebuster;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * activity où on joue au jeu. Lorsque la partie est finie, mène vers l'Activity highscore
 */
public class JeuActivity extends AppCompatActivity {

    private final static int[] adresses = {R.id.mole1, R.id.mole2, R.id.mole3, R.id.mole4, R.id.mole5, R.id.mole6, R.id.mole7, R.id.mole8, R.id.mole9};
    private final int TEMPSMAX = 1000; //temps avant que les taupes ne retournent dans leur trou
    private final int NIVEAUINITIAl = 2000; //temps initial entre l'appartition de chaque taupe

    /**
     * Création de la classe
     * @param savedInstanceState paramètre par défaut
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        Bundle extras = getIntent().getExtras();
        String nomJoueur = "";
        if (extras != null) {
            nomJoueur = extras.getString("nom");
        }

        final Joueur joueur = new Joueur(nomJoueur, 0, true);
        final ArrayList<Taupe> listeTaupes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listeTaupes.add(new Taupe((ImageButton) findViewById(adresses[i])));
            final int finalI = i;
            listeTaupes.get(i).getImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Si on clique sur une taupe

                    if (joueur.estActif()) {
                        //SI la partie est en cours

                        if (listeTaupes.get(finalI).estSortie()) {
                            //rentrer la taupe, ajuster le niveau, ajouter et afficher le score
                            listeTaupes.get(finalI).rentrer();
                            joueur.addScore(10);
                            DecimalFormat df = new DecimalFormat("#.##");
                            ((TextView) findViewById(R.id.scoreJoueur)).setText(df.format(joueur.getScore()));
                            joueur.addNiveau();
                            System.out.println("niveau = " + joueur.getNiveau());
                        }
                    }

                }
            });
        }

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //update des taupes

                if (joueur.estActif()) {
                    //Si le jeu est en cours

                    handler.postDelayed(this, joueur.getNiveau()); // set time here to refresh textView

                    //S'assurer que la première taupe n'apparaissent pas trop vite
                    if (joueur.getNiveau() == NIVEAUINITIAl) {
                        joueur.addNiveau();
                    } else {
                        //Une taupe apparait à un endroit aléatoire
                        int randIndex = (int) (Math.random() * 9);
                        if (!listeTaupes.get(randIndex).estSortie())
                            listeTaupes.get(randIndex).sortir();
                    }


                    //checker si les taupes sont sorties depuis trop longtemps
                    for (Taupe t : listeTaupes) {
                        if (t.estSortie()) {
                            if (Calendar.getInstance().getTimeInMillis() - t.getTempsSortie() > TEMPSMAX) {
                                //Arreter le jeu
                                t.sauvee();
                                joueur.stop();
                                Toast.makeText(JeuActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {
                    //une fois la partie terminée, attendre 1 secondes
                    handler.postDelayed(this, 1000); // set time here to refresh textView
                    if (joueur.getGoToHighScore()) {
                        //afficher un bouton qui mène vers les highscore
                        final Button nextButton = (Button) findViewById(R.id.nextButton);
                        nextButton.setVisibility(View.VISIBLE);
                        nextButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //when play is clicked show stop button and hide play button
                                Intent intent = new Intent(JeuActivity.this, HighScoreActivity.class);
                                intent.putExtra("nom", joueur.getNom());
                                intent.putExtra("score", joueur.getScore());
                                view.getContext().startActivity(intent);
                            }
                        });
                    }
                    joueur.setGoToHighScore(true);
                }

            }
        });

    }
}

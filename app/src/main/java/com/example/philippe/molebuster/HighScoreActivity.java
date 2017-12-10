package com.example.philippe.molebuster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * gère l'activité highscore, lit le fichier les contenants, le met à jour et offre un bouton rejouer
 */
public class HighScoreActivity extends AppCompatActivity {

    private MyRecyclerViewAdapter adapter;
    private String FILE = "highscore.txt";

    @Override
    /**
     * création de l'activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //ResetHighScore();
        //LoadHighScore(); //load un exemple de highscore

        //LIRE FICHIER
        ArrayList<Joueur> listeJoueurs = LireFichierHighScore();


       /* for (Joueur j : listeJoueurs) {
            j.show();
        }*/

        //ADD CURRENT PLAYER
        //METTRE SCORE EN GRAS
        Bundle extras = getIntent().getExtras();
        String nomCourant = "";
        double scoreCourant = 0;
        if (extras != null) {
            nomCourant = extras.getString("nom");
            scoreCourant = extras.getDouble("score");
        }
        listeJoueurs.add(new Joueur(nomCourant, scoreCourant, true));

        //CLasser liste
        Collections.sort(listeJoueurs);

        //Créer le recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, listeJoueurs);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        //ECRIRE FICHIER
        EcrireFichier(nomCourant, scoreCourant);


        //REJOUER
        final String nomAPasser = nomCourant;
        Button boutonRejouer = (Button) findViewById(R.id.boutonRejouer);
        boutonRejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScoreActivity.this, JeuActivity.class);
                intent.putExtra("nom", nomAPasser);
                view.getContext().startActivity(intent);
            }
        });
    }


    /**
     * lit les noms et les score dans le fichier de highscore et retourne une liste faite avec
     * @return la liste de joueurs avec leur noms et leur score
     */
    private ArrayList<Joueur> LireFichierHighScore() {
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(openFileInput(FILE)));

            // do reading, usually loop until end of file reading
            String mLine;
            String mLine2;
            String nom;
            double score;

            while ((mLine = reader.readLine()) != null && (mLine2 = reader.readLine()) != null) {
                //process line
                nom = mLine;
                score = Double.parseDouble(mLine2);
                //System.out.println("LE FICHIER EST : ");
                //System.out.println("L1 : " + mLine + " \n " + "L2 : " + mLine2);
                listeJoueurs.add(new Joueur(nom, score));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    e.printStackTrace();
                }
            }
        }
        return listeJoueurs;
    }

    /**
     * Ajoute le nom et le score du joueur qui vient de joueur au fichier de highscore
     * @param nomCourant nom du joueur
     * @param scoreCourant score du joueur
     */
    private void EcrireFichier(String nomCourant, double scoreCourant) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILE, MODE_APPEND)));

            writer.write(nomCourant);
            writer.write("\n");
            writer.write(String.valueOf(scoreCourant));
            writer.write("\n");
            //System.out.println("J'ÉCRIS: " + nomCourant + " + " + scoreCourant);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    //log the exception
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * vide le fichier de highscore
     */
    private void ResetHighScore() {

        try {

            FileOutputStream fileout = openFileOutput(FILE, MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fileout);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load un exemple de highscore situé dans /assets
     */
    private void LoadHighScore() {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(FILE)));
            FileOutputStream fileout = openFileOutput(FILE, MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileout);

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                //System.out.println("LINE = " + mLine);
                writer.write(mLine);
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    e.printStackTrace();
                }
            }
        }
    }
}

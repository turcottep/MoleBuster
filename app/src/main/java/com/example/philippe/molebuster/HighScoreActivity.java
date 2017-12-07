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

public class HighScoreActivity extends AppCompatActivity {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        ResetHighScore("highscore.txt");
        LoadHighScore("highscore.txt");

        //LIRE FICHIER
        ArrayList<Joueur> listeJoueurs = LireFichierHighScore("highscore.txt");
        for (Joueur j : listeJoueurs) {
            j.show();
        }

        //ADD CURRENT PLAYER
        //METTRE SCORE EN GRAS
        Bundle extras = getIntent().getExtras();
        String nomCourant = "simon";
        double scoreCourant = 12;
        if (extras != null) {
            nomCourant = extras.getString("nom");
            scoreCourant = extras.getDouble("score");
        } else {
            System.out.println("RIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIP");
        }
        System.out.println("EXTRAS = " + extras.getString("nom"));
        listeJoueurs.add(new Joueur(nomCourant, scoreCourant, true));

        //CLasser liste
        Collections.sort(listeJoueurs);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, listeJoueurs);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        //ECRIRE FICHIER
        EcrireFichier(nomCourant, scoreCourant, "highscore.txt");


        //REJOUER
        final String nomAPasser = nomCourant;
        Button boutonRejouer = (Button)findViewById(R.id.boutonRejouer);
        boutonRejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScoreActivity.this, JeuActivity.class);
                intent.putExtra("nom", nomAPasser);
                view.getContext().startActivity(intent);
            }
        });
    }

    private void EcrireFichier(String nomCourant, double scoreCourant, String file) {

        try {

            FileOutputStream fileout = openFileOutput("highscore.txt", MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileout);
            System.out.println("LE FICHIER EST : " + getFilesDir());
            writer.write(nomCourant);
            writer.write("\n");
            writer.write(String.valueOf(scoreCourant));
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ResetHighScore(String file) {

        try {

            FileOutputStream fileout = openFileOutput(file, MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fileout);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadHighScore(String file) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(file)));
            FileOutputStream fileout = openFileOutput(file, MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileout);

            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                System.out.println("LINE = " + mLine);
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


    private ArrayList<Joueur> LireFichierHighScore(String file) {
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(openFileInput(file)));

            // do reading, usually loop until end of file reading
            String mLine;
            String mLine2;
            String nom;
            double score;

            while ((mLine = reader.readLine()) != null && (mLine2 = reader.readLine()) != null) {
                //process line
                nom = mLine;
                score = Double.parseDouble(mLine2);
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
}

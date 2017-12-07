package com.example.philippe.molebuster;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JeuActivity extends AppCompatActivity {

    private final static int[] adresses = {R.id.mole1, R.id.mole2, R.id.mole3, R.id.mole4, R.id.mole5, R.id.mole6, R.id.mole7, R.id.mole8, R.id.mole9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        Bundle extras = getIntent().getExtras();
        String nomJoueur = "";
        if (extras != null) {
            nomJoueur = extras.getString("nom");
        }

        final Joueur joueur = new Joueur(nomJoueur);
        final ArrayList<Taupe> listeTaupes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listeTaupes.add(new Taupe((ImageButton) findViewById(adresses[i])));
            final int finalI = i;
            listeTaupes.get(i).getImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Si on clique sur une taupe
                    //Toast.makeText(JeuActivity.this, "WOOO ca marche", Toast.LENGTH_SHORT).show();
                    if (listeTaupes.get(finalI).estSortie()) {
                        listeTaupes.get(finalI).rentrer();
                        joueur.addScore(10);
                        ((TextView) findViewById(R.id.scoreJoueur)).setText(String.valueOf(joueur.getScore()));
                        joueur.addNiveau();
                        System.out.println("niveau = " + joueur.getNiveau());
                    }
                }
            });
        }

        final int i = 0;
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //i = 0;
                // upadte textView here

                handler.postDelayed(this, joueur.getNiveau()); // set time here to refresh textView
                //System.out.println(i++);
                int randIndex = (int) (Math.random() * 9);
                System.out.println("pop : " + randIndex);
                listeTaupes.get(randIndex).sortir();

            }
        });

    }
}

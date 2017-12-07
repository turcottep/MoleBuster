package com.example.philippe.molebuster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class JeuActivity extends AppCompatActivity {

    private final static int[] adresses = {R.id.mole1,R.id.mole2,R.id.mole3,R.id.mole4,R.id.mole5,R.id.mole6,R.id.mole7,R.id.mole8,R.id.mole9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        final ArrayList<Taupe> listeTaupes = new ArrayList<>();
        for (int i=0;i<9;i++){
            listeTaupes.add(new Taupe((ImageButton)findViewById(adresses[i])));
            final int finalI = i;
            listeTaupes.get(i).getImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Si on clique sur une taupe
                    //Toast.makeText(JeuActivity.this, "WOOO ca marche", Toast.LENGTH_SHORT).show();
                    listeTaupes.get(finalI).rentrer();
                }
            });
        }

    }
}

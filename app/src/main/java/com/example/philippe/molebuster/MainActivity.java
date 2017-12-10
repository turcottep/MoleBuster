package com.example.philippe.molebuster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity principale, avec image de backgourn, case qui demande le nom et bouton vers l'Activity jeu
 */
public class MainActivity extends AppCompatActivity {

    private EditText mNameInput;
    private Button mPlayButton;

    /**
     * Création de l'activity
     * @param savedInstanceState paramètre par défaut
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayButton.setEnabled(false);

        //si le nom est bel et bien écrit
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //bouton qui mène vers l'activity jeu
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JeuActivity.class);
                String nom = String.valueOf(mNameInput.getText());
                double score =10;
                intent.putExtra("nom", nom);
                intent.putExtra("score", score);
                view.getContext().startActivity(intent);
            }
        });
    }
}

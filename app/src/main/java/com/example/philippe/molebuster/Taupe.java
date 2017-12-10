package com.example.philippe.molebuster;

import android.widget.ImageButton;

import java.util.Calendar;

/**
 * Created by Philippe on 2017-12-06.
 */

/**
 * Classe qui gère la taupe, ses drawables ainsi que le temps durant lequel elle est sortie
 */
public class Taupe {
    private Boolean sortie;
    //private int adresseImage;
    private ImageButton imageButton;
    private long tempsSortie;

    /**
     * Constructeur
     * @param imageButton l'imageButton auquel la taupe est reliée
     */
    public Taupe(ImageButton imageButton) {
        this.sortie = false;
        this.imageButton = imageButton;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public Boolean estSortie(){
        return sortie;
    }

    /**
     * rentre la taupe en changant l'image
     */
    public void rentrer(){
        sortie = false;
        imageButton.setImageResource(R.drawable.trou);
    }

    /**
     * sort la taupe et sauvegarde le temps auquel elle est sortie
     */
    public void sortir(){
        sortie = true;
        imageButton.setImageResource(R.drawable.mole);
        tempsSortie = Calendar.getInstance().getTimeInMillis();
    }

    public long getTempsSortie() {
        return tempsSortie;
    }

    /**
     * change l'image pour une taupe qui a réussi à se sauver si c'est le cas
     */
    public void sauvee(){
        imageButton.setImageResource(R.drawable.sauvee);
    }
}

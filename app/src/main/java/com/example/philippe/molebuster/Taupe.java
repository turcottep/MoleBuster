package com.example.philippe.molebuster;

import android.widget.ImageButton;

import java.util.Calendar;

/**
 * Created by Philippe on 2017-12-06.
 */

public class Taupe {
    private Boolean sortie;
    //private int adresseImage;
    private ImageButton imageButton;
    private long tempsSortie;

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

    public void rentrer(){
        sortie = false;
        imageButton.setImageResource(R.drawable.trou);
    }

    public void sortir(){
        sortie = true;
        imageButton.setImageResource(R.drawable.mole);
        tempsSortie = Calendar.getInstance().getTimeInMillis();
    }

    public long getTempsSortie() {
        return tempsSortie;
    }

    public void sauvee(){
        imageButton.setImageResource(R.drawable.sauvee);
    }
}

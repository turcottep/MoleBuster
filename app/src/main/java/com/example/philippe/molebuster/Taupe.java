package com.example.philippe.molebuster;

import android.widget.ImageButton;

/**
 * Created by Philippe on 2017-12-06.
 */

public class Taupe {
    private Boolean sortie;
    //private int adresseImage;
    private ImageButton imageButton;

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
    }

}

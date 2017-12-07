package com.example.philippe.molebuster;

import android.support.annotation.NonNull;

/**
 * Created by Philippe on 2017-12-05.
 */

public class Joueur implements Comparable<Joueur> {
    private String nom;
    private double score;
    private Boolean courant;

    public Joueur(String nom, double score, Boolean courant) {
        this.nom = nom;
        this.score = score;
        this.courant = courant;
    }

    public Joueur(String nom, double score) {
        this.nom = nom;
        this.score = score;
        this.courant=false;

    }

    public String getNom() {
        return nom;
    }

    public Boolean getCourant() {
        return courant;
    }

    public void setCourant(Boolean courant) {
        this.courant = courant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void show() {
        System.out.println(nom + " : " + score);
    }

    @Override
    public int compareTo(@NonNull Joueur joueur) {
        return (int) (joueur.getScore() - this.getScore());
    }
}

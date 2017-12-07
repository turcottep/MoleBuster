package com.example.philippe.molebuster;

import android.support.annotation.NonNull;

/**
 * Created by Philippe on 2017-12-05.
 */

public class Joueur implements Comparable<Joueur> {
    private String nom;
    private double score;
    private Boolean courant;
    private Boolean joue;
    private Boolean goToHighScore;

    public int getNiveau() {
        return niveau;
    }

    public void addNiveau() {
        if (niveau > 300) {
            niveau = (int) (niveau - Math.sqrt(niveau));
        } else if (niveau > 200) niveau--;
    }

    private int niveau;

    public Boolean getGoToHighScore() {
        return goToHighScore;
    }

    public void setGoToHighScore(Boolean goToHighScore) {
        this.goToHighScore = goToHighScore;
    }

    public Joueur(String nom, double score, Boolean courant) {
        this.nom = nom;
        this.score = score;
        this.courant = courant;
        this.niveau = 2000;
        this.joue = true;
        this.goToHighScore=false;
    }

    public Joueur(String nom, double score) {
        this(nom, score, false);
    }

    public Joueur(String nom) {
        this(nom, 0, false);
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

    public void stop(){
        this.joue = false;
    }

    public Boolean estJoue(){
        return joue;
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

    public void addScore(double score) {
        this.score += score;
    }

    public void show() {
        System.out.println(nom + " : " + score);
    }

    @Override
    public int compareTo(@NonNull Joueur joueur) {
        return (int) (joueur.getScore() - this.getScore());
    }
}

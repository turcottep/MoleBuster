package com.example.philippe.molebuster;

import android.support.annotation.NonNull;

/**
 * Created by Philippe on 2017-12-05.
 */

/**
 * Classe qui conserve le nom, le score ainsi que d'autrews variables nécessaires au jeu
 */
public class Joueur implements Comparable<Joueur> {
    private String nom;
    private double score;
    private Boolean actif; //est actif
    private Boolean goToHighScore;

    public int getNiveau() {
        return niveau;
    }

    /**
     * rend le jeu plus difficile en diminuant le temps entre chaque apparition de taupe
     */
    public void addNiveau() {
        if (niveau > 300) {
            niveau = (int) (niveau - Math.sqrt(niveau));
        } else if (niveau > 150) niveau--;
    }

    private int niveau;

    public Boolean getGoToHighScore() {
        return goToHighScore;
    }

    /**
     * Boolean qui est utilisé lorssque la partie fini
     * @param goToHighScore si on va vers l'Activity highscore
     */
    public void setGoToHighScore(Boolean goToHighScore) {
        this.goToHighScore = goToHighScore;
    }

    /**
     * Constructeur par défaut
     * @param nom nom du joueur
     * @param score score du joueur
     * @param actif si le joueur est actif
     */
    public Joueur(String nom, double score, Boolean actif) {
        this.nom = nom;
        this.score = score;
        this.actif = actif;
        this.niveau = 2000; //niveau initial
        this.goToHighScore = false;
    }

    /**
     * Constructeur
     * @param nom nom du joueur
     * @param score score du joueur
     */
    public Joueur(String nom, double score) {
        this(nom, score, false);
    }

    /**
     * Constructeur
     * @param nom nom du joueur
     */
    public Joueur(String nom) {
        this(nom, 0, false);
    }

    public String getNom() {
        return nom;
    }

    /**
     * rend le joueur inactif
     */
    public void stop() {
        this.actif = false;
    }

    public Boolean estActif() {
        return actif;
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

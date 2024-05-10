package org.example.projet_java_2024.backend;

import java.time.LocalDateTime;

public class Resultat {
    private Athlete athlete;
    private int score;
    private LocalDateTime temps;
    private String medaille;

    public Resultat(Athlete athlete, int score, LocalDateTime temps, String medaille) {
        this.athlete = athlete;
        this.score = score;
        this.temps = temps;
        this.medaille = medaille;
    }

    public Athlete getAthlete() {
        return athlete;
    }
    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public LocalDateTime getTemps() {
        return temps;
    }
    public void setTemps(LocalDateTime temps) {
        this.temps = temps;
    }
    public String getMedaille() {
        return medaille;
    }
    public void setMedaille(String medaille) {
        this.medaille = medaille;
    }
}

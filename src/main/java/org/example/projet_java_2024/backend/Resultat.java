package org.example.projet_java_2024.backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Resultat {
    private int score;
    private DateTimeFormatter temps;
    private String medaille;

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
    public DateTimeFormatter getTemps() {
        DateTimeFormatter temps = DateTimeFormatter.ofPattern("mm:ss:SSS");
        return temps;
    }
    public void setTemps(DateTimeFormatter temps) {this.temps = temps;}
    public String getMedaille() {return medaille;}
    public void setMedaille(String medaille) {this.medaille = medaille;}
}

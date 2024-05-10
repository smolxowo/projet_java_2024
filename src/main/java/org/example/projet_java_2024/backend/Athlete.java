package org.example.projet_java_2024.backend;

import java.util.ArrayList;

public class Athlete {
    private String nomAthlete;
    private String sexe;
    private String pays;
    private int age;
    private int nbParticipation;        //le nombre de participations aux JO précédents

    //Constructeur
    public Athlete(String nomAthlete, String sexe, String pays, int age, int nbParticipation) {
        this.nomAthlete = nomAthlete;
        this.sexe = sexe;
        this.pays = pays;
        this.age = age;
        this.nbParticipation = nbParticipation;
    }

    //Getters et Setters
    public String getNomAthlete() {return nomAthlete;}
    public void setNomAthlete(String nomAthlete) {this.nomAthlete = nomAthlete;}
    public String getSexe() {return sexe;}
    public void setSexe(String sexe) {this.sexe = sexe;}
    public String getPays() {return pays;}
    public void setPays(String pays) {this.pays = pays;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public int getNbParticipation() {return nbParticipation;}
    public void setNbParticipation(int nbParticipation) {this.nbParticipation = nbParticipation;}
}

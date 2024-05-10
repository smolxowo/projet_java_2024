package org.example.projet_java_2024.backend;

public class Participants {
    private Athlete athlete;
    private EvenementSportif evenementSportif;

    public Participants(Athlete athlete, EvenementSportif evenementSportif) {
        this.athlete = athlete;
        this.evenementSportif = evenementSportif;
    }

    public Athlete getAthlete() {
        return athlete;
    }
    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
    public EvenementSportif getEvenementSportif() {
        return evenementSportif;
    }
    public void setEvenementSportif(EvenementSportif evenementSportif) {
        this.evenementSportif = evenementSportif;
    }
}

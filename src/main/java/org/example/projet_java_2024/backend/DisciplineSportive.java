package org.example.projet_java_2024.backend;

import java.util.ArrayList;

public class DisciplineSportive {
    private String nomDS;
    private ArrayList<Athlete> participantDS;

    public DisciplineSportive(String nomDS, ArrayList<Athlete> participantDS) {
        this.nomDS = nomDS;
        this.participantDS = participantDS;
    }

    public String getNomDS() {return nomDS;}
    public void setNomDS(String nomDS) {this.nomDS = nomDS;}
    public ArrayList<Athlete> getParticipantDS() {return participantDS;}
    public void setParticipantDS(ArrayList<Athlete> participantDS) {this.participantDS = participantDS;}

}

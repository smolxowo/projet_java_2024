package org.example.projet_java_2024.backend;

import java.util.ArrayList;
import java.util.HashMap;

public class EvenementSportif extends DisciplineSportive{
    private String nomEvnt;
    private HashMap<String, ArrayList<Athlete>> participantES;

    public EvenementSportif(String nomDS, ArrayList<Athlete> participantDS, String nomEvnt, HashMap<String, ArrayList<Athlete>> participantES) {
        super(nomDS, participantDS);
        this.nomEvnt = nomEvnt;
        this.participantES = participantES;
    }

    public String getNomEvnt() {return nomEvnt;}
    public void setNomEvnt(String nomEvnt) {this.nomEvnt = nomEvnt;}
    public HashMap<String, ArrayList<Athlete>> getParticipantES() {return participantES;}
    public void setParticipantES(HashMap<String, ArrayList<Athlete>> participantES) {this.participantES = participantES;}

}

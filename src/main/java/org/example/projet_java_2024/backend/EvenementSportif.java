package org.example.projet_java_2024.backend;

import java.util.ArrayList;
import java.util.List;

public class EvenementSportif extends DisciplineSportive{
    private String nomEvnt;
    private List<Participants> participantES;

    public EvenementSportif(String nomDS, List<Athlete> participantDS, String nomEvnt, List<Participants> participantES) {
        super(nomDS, participantDS);
        this.nomEvnt = nomEvnt;
        this.participantES = participantES;
    }

    public String getNomEvnt() {
        return nomEvnt;
    }
    public void setNomEvnt(String nomEvnt) {
        this.nomEvnt = nomEvnt;
    }
    public List<Participants> getParticipantES() {
        return participantES;
    }
    public void setParticipantES(List<Participants> participantES) {
        this.participantES = participantES;
    }
}

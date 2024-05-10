package org.example.projet_java_2024.backend;

import java.util.ArrayList;
import java.util.List;

public class DisciplineSportive {
    private String nomDS;
    private List<Athlete> participantDS;

    public DisciplineSportive(String nomDS, List<Athlete> participantDS) {
        this.nomDS = nomDS;
        this.participantDS = participantDS;
    }

    public String getNomDS() {
        return nomDS;
    }
    public void setNomDS(String nomDS) {
        this.nomDS = nomDS;
    }
    public List<Athlete> getParticipantDS() {
        return participantDS;
    }
    public void setParticipantDS(List<Athlete> participantDS) {
        this.participantDS = participantDS;
    }

}

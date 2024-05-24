package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class EvenementSportifGestionnaire extends DatabaseGestionnaire<EvenementSportif> {
    private final List<EvenementSportif> evenementsSportifs;

    public EvenementSportifGestionnaire() {
        super("/org/example/projet_java_2024/database/evenements-sportifs.json");
        this.evenementsSportifs = this.items;
    }

    @Override
    protected TypeReference<List<EvenementSportif>> getTypeReference() {
        return new TypeReference<List<EvenementSportif>>() {};
    }

    public List<EvenementSportif> getAllEvenementsSportifs() {
        return evenementsSportifs;
    }

    public EvenementSportif getEvenementSportifById(int id) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == id) {
                return evenementSportif;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + id);
    }

    public int addEvenementSportif(String nom, int disciplineSportifId) {
        EvenementSportif evenementSportif = new EvenementSportif(evenementsSportifs.size() + 1, nom, disciplineSportifId, new ArrayList<>()); // Nouvelle discipline sans participants
        evenementsSportifs.add(evenementSportif);
        saveToJSON();
        return evenementSportif.getId();
    }

    public void deleteEvenementSportif(int id) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == id) {
                evenementsSportifs.remove(evenementSportif);
                saveToJSON();
                return;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + id);
    }

    public int updateEvenementSportif(int id, String nom, int disciplineSportifId, List<Integer> participantsId) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == id) {
                evenementSportif.setNom(nom);
                evenementSportif.setDisciplineSportifId(disciplineSportifId);
                evenementSportif.setParticipantsId(participantsId);
                saveToJSON();
                return id;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + id);
    }

    public int addParticipantToEvenementSportif(int evenementSportifId, int participantId) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == evenementSportifId) {
                evenementSportif.getParticipantsId().add(participantId);
                saveToJSON();
                return evenementSportifId;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + evenementSportifId);
    }

    public int removeParticipantFromEvenementSportif(int evenementSportifId, int participantId) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == evenementSportifId) {
                evenementSportif.getParticipantsId().remove(participantId);
                saveToJSON();
                return evenementSportifId;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + evenementSportifId);
    }
}
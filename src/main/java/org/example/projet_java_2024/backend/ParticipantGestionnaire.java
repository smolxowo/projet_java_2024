package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class ParticipantGestionnaire extends DatabaseGestionnaire<Participant> {
    private final List<Participant> participants;

    public ParticipantGestionnaire() {
        super("/org/example/projet_java_2024/database/participants.json");
        this.participants = this.items;
    }

    @Override
    protected TypeReference<List<Participant>> getTypeReference() {
        return new TypeReference<List<Participant>>() {};
    }

    public List<Participant> getAllParticipants() {
        return participants;
    }

    public Participant getParticipantById(int id) {
        for (Participant participant : participants) {
            if (participant.getId() == id) {
                return participant;
            }
        }
        throw new IllegalArgumentException("Participant not found: " + id);
    }

    public int addParticipant(int athleteId, int evenementSportifId) {
        Participant participant = new Participant(participants.size() + 1, athleteId, evenementSportifId);
        participants.add(participant);
        saveToJSON();
        return participant.getId();
    }

    public void deleteParticipant(int id) {
        for (Participant participant : participants) {
            if (participant.getId() == id) {
                participants.remove(participant);
                saveToJSON();
                return;
            }
        }
        throw new IllegalArgumentException("Participant not found: " + id);
    }

    public int updateParticipant(int id, int athleteId, int evenementSportifId) {
        for (Participant participant : participants) {
            if (participant.getId() == id) {
                participant.setAthleteId(athleteId);
                participant.setEvenementSportifId(evenementSportifId);
                saveToJSON();
                return id;
            }
        }
        throw new IllegalArgumentException("Participant not found: " + id);
    }
}
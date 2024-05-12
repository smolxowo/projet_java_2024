package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class DisciplineSportiveGestionnaire extends DatabaseGestionnaire<DisciplineSportive> {
    private final List<DisciplineSportive> disciplinesSportives;

    public DisciplineSportiveGestionnaire() {
        super("/org/example/projet_java_2024/database/disciplines-sportives.json");
        this.disciplinesSportives = this.items;
    }

    @Override
    protected TypeReference<List<DisciplineSportive>> getTypeReference() {
        return new TypeReference<List<DisciplineSportive>>() {};
    }

    public List<DisciplineSportive> getAllDisciplinesSportives() {
        return disciplinesSportives;
    }

    public DisciplineSportive getDisciplineSportiveById(int id) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == id) {
                return disciplineSportive;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + id);
    }

    public int addDisciplineSportif(String nom, List<Integer> participantsId) {
        DisciplineSportive disciplineSportive = new DisciplineSportive(disciplinesSportives.size() + 1, nom, participantsId);
        disciplinesSportives.add(disciplineSportive);
        saveToJSON();
        return disciplineSportive.getId();
    }

    public void deleteDisciplineSportif(int id) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == id) {
                disciplinesSportives.remove(disciplineSportive);
                saveToJSON();
                return;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + id);
    }

    public int updateDisciplineSportif(int id, String nom, List<Integer> participantsId) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == id) {
                disciplineSportive.setNom(nom);
                disciplineSportive.setParticipantId(participantsId);
                saveToJSON();
                return id;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + id);
    }

    public int addParticipantToDisciplineSportif(int disciplineSportifId, int participantId) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == disciplineSportifId) {
                disciplineSportive.getParticipantId().add(participantId);
                saveToJSON();
                return disciplineSportifId;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + disciplineSportifId);
    }

    public int removeParticipantFromDisciplineSportif(int disciplineSportifId, int participantId) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == disciplineSportifId) {
                disciplineSportive.getParticipantId().remove(participantId);
                saveToJSON();
                return disciplineSportifId;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + disciplineSportifId);
    }
}
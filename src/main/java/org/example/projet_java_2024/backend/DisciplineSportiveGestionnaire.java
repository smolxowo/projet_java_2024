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

    public DisciplineSportive getDisciplineSportiveByName(String name) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getNom().equals(name)) {
                return disciplineSportive;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + name);
    }

    public int addDisciplineSportive(String nom, List<Integer> athleteId) {
        int nextId = getNextId();
        DisciplineSportive disciplineSportive = new DisciplineSportive(nextId, nom, athleteId);
        disciplinesSportives.add(disciplineSportive);
        saveToJSON();
        return nextId;
    }

    public void deleteDisciplineSportive(int id) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == id) {
                disciplinesSportives.remove(disciplineSportive);
                saveToJSON();
                return;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + id);
    }

    public int addAthleteToDisciplineSportive(int disciplineSportiveId, int athleteId) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == disciplineSportiveId) {
                disciplineSportive.getAthletesId().add(athleteId);
                saveToJSON();
                return disciplineSportiveId;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + disciplineSportiveId);
    }

    public int removeAthleteFromDisciplineSportive(int disciplineSportiveId, int athleteId) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == disciplineSportiveId) {
                disciplineSportive.getAthletesId().remove(Integer.valueOf(athleteId));
                saveToJSON();
                return disciplineSportiveId;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + disciplineSportiveId);
    }

    public String getDisciplineNameById(int disciplineId) {
        for (DisciplineSportive disciplineSportive : disciplinesSportives) {
            if (disciplineSportive.getId() == disciplineId) {
                return disciplineSportive.getNom();
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + disciplineId);
    }
}

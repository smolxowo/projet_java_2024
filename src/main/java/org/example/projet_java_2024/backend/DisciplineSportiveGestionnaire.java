package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.example.projet_java_2024.frontend.AccueilController.EVENEMENT_GESTIONNAIRE;

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

    public void deleteDisciplineSportive(int disciplineId) {
        // Collect IDs of events to be deleted
        List<Integer> evenementIdsToDelete = new ArrayList<>();
        for (EvenementSportif evenementSportif : EVENEMENT_GESTIONNAIRE.getAllEvenementsSportifs()) {
            if (evenementSportif.getDisciplineSportiveId() == disciplineId) {
                evenementIdsToDelete.add(evenementSportif.getId());
            }
        }

        // Delete events after collecting IDs
        for (int evenementId : evenementIdsToDelete) {
            EVENEMENT_GESTIONNAIRE.deleteEvenementSportif(evenementId);
        }

        boolean isRemoved = disciplinesSportives.removeIf(discipline -> discipline.getId() == disciplineId);

        saveToJSON();

        if (!isRemoved) {
            throw new IllegalArgumentException("Discipline Sportive not found: " + disciplineId);
        }
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

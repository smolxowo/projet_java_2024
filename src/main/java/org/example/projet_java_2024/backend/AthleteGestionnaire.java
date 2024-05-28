package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

import static org.example.projet_java_2024.frontend.AccueilController.DISCIPLINE_GESTIONNAIRE;
import static org.example.projet_java_2024.frontend.AccueilController.EVENEMENT_GESTIONNAIRE;

public class AthleteGestionnaire extends DatabaseGestionnaire<Athlete> {
    private final List<Athlete> athletes;

    public AthleteGestionnaire() {
        super("/org/example/projet_java_2024/database/athletes.json");

        // Reference parent items, to be named athletes. Easier to understand.
        this.athletes = this.items;
    }

    @Override
    protected TypeReference<List<Athlete>> getTypeReference() {
        return new TypeReference<List<Athlete>>() {};
    }

    public List<Athlete> getAllAthletes() {
        return athletes;
    }

    public Athlete getAthleteById(int id) {
        for (Athlete athlete : athletes) {
            if (athlete.getId() == id) {
                return athlete;
            }
        }
        throw new IllegalArgumentException("Athlete not found: " + id);
    }

    public Athlete getAthleteByName(String name) {
        for (Athlete athlete : athletes) {
            if (athlete.getNom().equals(name)) {
                return athlete;
            }
        }
        throw new IllegalArgumentException("Athlete not found: " + name);
    }

    public String getAthleteNameById(int id) {
    for (Athlete athlete : athletes) {
        if (athlete.getId() == id) {
            return athlete.getNom();
        }
    }
    throw new IllegalArgumentException("Athlete not found: " + id);
}

    public List<Athlete> getAthletesByProperty(String property, String value) {
        List<Athlete> result = new ArrayList<>();
        for (Athlete athlete : athletes) {
            switch (property) {
                case "nomAthlete":
                    if (athlete.getNom().equals(value)) {
                        result.add(athlete);
                    }
                    break;
                case "sexe":
                    if (athlete.getSexe().equals(value)) {
                        result.add(athlete);
                    }
                    break;
                case "pays":
                    if (athlete.getPays().equals(value)) {
                        result.add(athlete);
                    }
                    break;
                case "age":
                    if (athlete.getAge() == Integer.parseInt(value)) {
                        result.add(athlete);
                    }
                    break;
                case "nbParticipation":
                    if (athlete.getNbParticipation() == Integer.parseInt(value)) {
                        result.add(athlete);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown property: " + property);
            }
        }
        return result;
    }

    public int addAthlete(String nomAthlete,
                          String sexe,
                          String pays,
                          int age,
                          int nbParticipation) {

        int nextId = getNextId();

        Athlete athlete = new Athlete(
                nextId,
                nomAthlete,
                sexe,
                pays,
                age,
                nbParticipation
        );

        athletes.add(athlete);

        saveToJSON();

        return nextId;
    }

    public void deleteAthlete(int athleteId) {
        // Cascade delete from discipline
        for (DisciplineSportive disciplineSportive : DISCIPLINE_GESTIONNAIRE.getAllDisciplinesSportives()) {
            DISCIPLINE_GESTIONNAIRE.removeAthleteFromDisciplineSportive(disciplineSportive.getId(), athleteId);
        }

        // Cascade delete from EvenementSportif
        for (EvenementSportif evenementSportif : EVENEMENT_GESTIONNAIRE.getAllEvenementsSportifs()) {
            EVENEMENT_GESTIONNAIRE.removeAthleteFromEvenementSportif(evenementSportif.getId(), athleteId);
        }

        for (Athlete athlete : athletes) {
            if (athlete.getId() == athleteId) {
                athletes.remove(athlete);
                saveToJSON();
                return;
            }
        }
    }

    public int updateAthlete(int id, String nomAthlete, String sexe, String pays, int age, int nbParticipation) {
        for (Athlete athlete : athletes) {
            if (athlete.getId() == id) {
                athlete.setNom(nomAthlete);
                athlete.setSexe(sexe);
                athlete.setPays(pays);
                athlete.setAge(age);
                athlete.setNbParticipation(nbParticipation);

                saveToJSON();
                return id;
            }
        }

        throw new IllegalArgumentException("Athlete not found: " + id);
    }
}
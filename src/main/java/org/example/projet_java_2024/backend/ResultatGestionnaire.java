package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.projet_java_2024.frontend.AccueilController.EVENEMENT_GESTIONNAIRE;
import static org.example.projet_java_2024.frontend.AccueilController.ATHLETE_GESTIONNAIRE;


public class ResultatGestionnaire extends DatabaseGestionnaire<Resultat> {
    private final List<Resultat> resultats;

    public ResultatGestionnaire() {
        super("/org/example/projet_java_2024/database/resultats.json");
        this.resultats = this.items;
    }

    @Override
    protected TypeReference<List<Resultat>> getTypeReference() {
        return new TypeReference<List<Resultat>>() {
        };
    }

    public List<Resultat> getAllResultats() {
        return resultats;
    }

    public Resultat getResultatById(int id) {
        for (Resultat resultat : resultats) {
            if (resultat.getId() == id) {
                return resultat;
            }
        }
        throw new IllegalArgumentException("Resultat not found: " + id);
    }

    public String getMedailleById(int id) {
    for (Resultat resultat : resultats) {
        if (resultat.getId() == id) {
            return resultat.getMedaille();
        }
    }
    throw new IllegalArgumentException("Resultat not found: " + id);
}


    public void setPays(Resultat resultat, String pays) {
        Athlete athlete = ATHLETE_GESTIONNAIRE.getAthleteById(resultat.getAthleteId());
        athlete.setPays(pays);
        saveToJSON();
    }

    public void setDiscipline(Resultat resultat, String discipline) {
        EvenementSportif event = EVENEMENT_GESTIONNAIRE.getEvenementSportifById(resultat.getEvenementSportifId());
        event.setNom(discipline);
        saveToJSON();
    }

    public void setAthlete(Resultat resultat, String athleteName) {
        Athlete athlete = ATHLETE_GESTIONNAIRE.getAthleteByName(athleteName);
        resultat.setAthleteId(athlete.getId());
        saveToJSON();
    }

    public int addResultat(int athleteId, int evenementSportifId, int score, String temps, String medaille) {
        int nextId = getNextId();
        Resultat resultat = new Resultat(
                nextId,
                athleteId,
                evenementSportifId,
                score,
                temps,
                medaille
        );
        resultats.add(resultat);
        saveToJSON();
        return resultat.getId();
    }

    public void deleteResultat(int resultatId) {
        // ** No cascade delete for end child

        for (Resultat resultat : resultats) {
            if (resultat.getId() == resultatId) {
                resultats.remove(resultat);
                saveToJSON();
                return;
            }
        }
        throw new IllegalArgumentException("Resultat not found: " + resultatId);
    }

    public int updateResultat(int id, int athleteId, int evenementSportifId, int score, String temps, String medaille) {
        for (Resultat resultat : resultats) {
            if (resultat.getId() == id) {
                resultat.setAthleteId(athleteId);
                resultat.setEvenementSportifId(evenementSportifId);
                resultat.setScore(score);
                resultat.setDate(temps);
                resultat.setMedaille(medaille);
                saveToJSON();
                return id;
            }
        }
        throw new IllegalArgumentException("Resultat not found: " + id);
    }

    public Map<String, Map<String, Integer>> getMedalsByCountry() {
        Map<String, Map<String, Integer>> medalsByCountry = new HashMap<>();

        for (Resultat resultat : resultats) {
            String country = ATHLETE_GESTIONNAIRE.getAthleteById(resultat.getAthleteId()).getPays();
            String medal = resultat.getMedaille();

            if (!medalsByCountry.containsKey(country)) {
                medalsByCountry.put(country, new HashMap<>());
            }

            medalsByCountry.get(country).put(medal, medalsByCountry.get(country).getOrDefault(medal, 0) + 1);
        }

        return medalsByCountry;
    }

    public Map<String, List<String>> getMedalistsByDiscipline() {
        Map<String, List<String>> medalistsByDiscipline = new HashMap<>();

        for (Resultat resultat : resultats) {
            String discipline = EVENEMENT_GESTIONNAIRE.getEvenementSportifById(resultat.getEvenementSportifId()).getNom();
            String athlete = ATHLETE_GESTIONNAIRE.getAthleteById(resultat.getAthleteId()).getNom();

            if (!medalistsByDiscipline.containsKey(discipline)) {
                medalistsByDiscipline.put(discipline, new ArrayList<>());
            }

            medalistsByDiscipline.get(discipline).add(athlete);
        }

        return medalistsByDiscipline;
    }
}
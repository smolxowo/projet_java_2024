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

    /*
    Par exemple :
    {
    France: {Or: 2, Argent: 3, Bronze: 1}
    }
     */
    public Map<String, Map<String, Integer>> getMedalsByCountry() {
        Map<String, Map<String, Integer>> medalsByCountry = new HashMap<>();

        for (Resultat resultat : resultats) {
            String country = ATHLETE_GESTIONNAIRE.getAthleteById(resultat.getAthleteId()).getPays();
            String medal = resultat.getMedaille();

            // Ajouter un nouveau pays
            if (!medalsByCountry.containsKey(country)) {
                medalsByCountry.put(country, new HashMap<>());
            }

            // Ajouter une nouvelle médaille
            medalsByCountry.get(country).put(
                    medal,
                    medalsByCountry.get(country).getOrDefault(medal, 0) + 1
            );
        }

        return medalsByCountry;
    }

    /*
    Par exemple :
    {
    Discipline: {Or: 2, Argent: 3, Bronze: 1}
    }
     */
    public Map<String, Map<String, Integer>> getMedalsByDiscipline() {
        Map<String, Map<String, Integer>> medalsByDiscipline = new HashMap<>();

        for (Resultat resultat : resultats) {
            String discipline = EVENEMENT_GESTIONNAIRE.getEvenementSportifById(resultat.getEvenementSportifId()).getNom();
            String medal = resultat.getMedaille();

            // Ajouter une nouvelle discipline
            if (!medalsByDiscipline.containsKey(discipline)) {
                medalsByDiscipline.put(discipline, new HashMap<>());
            }

            // Ajouter une nouvelle médaille
            medalsByDiscipline.get(discipline).put(
                    medal,
                    medalsByDiscipline.get(discipline).getOrDefault(medal, 0) + 1
            );
        }

        return medalsByDiscipline;
    }
}
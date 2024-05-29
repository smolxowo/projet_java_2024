package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class ResultatGestionnaire extends DatabaseGestionnaire<Resultat> {
    private final List<Resultat> resultats;

    public ResultatGestionnaire() {
        super("/org/example/projet_java_2024/database/resultats.json");
        this.resultats = this.items;
    }

    @Override
    protected TypeReference<List<Resultat>> getTypeReference() {
        return new TypeReference<List<Resultat>>() {};
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
}
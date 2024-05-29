package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.example.projet_java_2024.frontend.AccueilController.RESULTAT_GESTIONNAIRE;

public class EvenementSportifGestionnaire extends DatabaseGestionnaire<EvenementSportif> {
    private final List<EvenementSportif> evenementsSportifs;

    public EvenementSportifGestionnaire() {
        super("/org/example/projet_java_2024/database/evenements-sportifs.json");
        this.evenementsSportifs = this.items;
    }

    @Override
    protected TypeReference<List<EvenementSportif>> getTypeReference() {
        return new TypeReference<List<EvenementSportif>>() {
        };
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

    public EvenementSportif getEvenementSportifByName(String name) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getNom().equals(name)) {
                return evenementSportif;
            }
        }
        throw new IllegalArgumentException("Discipline Sportive not found: " + name);
    }

    public List<EvenementSportif> getEvenementsSportifsByAthleteId(int athleteId) {
        List<EvenementSportif> athleteEvents = new ArrayList<>();
        for (EvenementSportif event : evenementsSportifs) {
            if (event.getAthletesId().contains(athleteId)) {
                athleteEvents.add(event);
            }
        }
        return athleteEvents;
    }

    public String getEvenementSportifNamebyID(int id) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == id) {
                return evenementSportif.getNom();
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + id);
    }

    public int addEvenementSportif(String nom, int disciplineSportiveId) {
        int nextId = getNextId();
        EvenementSportif evenementSportif = new EvenementSportif(
                nextId,
                nom,
                disciplineSportiveId,
                new ArrayList<>()
        );
        evenementsSportifs.add(evenementSportif);
        saveToJSON();
        return evenementSportif.getId();
    }

    public void deleteEvenementSportif(int evenementId) {
        // Cascade delete from Resultat
        Iterator<Resultat> resultatIterator = RESULTAT_GESTIONNAIRE.getAllResultats().iterator();
        while (resultatIterator.hasNext()) {
            Resultat resultat = resultatIterator.next();
            if (resultat.getEvenementSportifId() == evenementId) {
                resultatIterator.remove();
                RESULTAT_GESTIONNAIRE.deleteResultat(resultat.getId());
            }
        }

        // Collect the events to be removed
        Iterator<EvenementSportif> evenementIterator = evenementsSportifs.iterator();
        boolean found = false;
        while (evenementIterator.hasNext()) {
            EvenementSportif evenementSportif = evenementIterator.next();
            if (evenementSportif.getId() == evenementId) {
                evenementIterator.remove();
                found = true;
            }
        }

        saveToJSON();

        if (!found) {
            throw new IllegalArgumentException("Evenement Sportif not found: " + evenementId);
        }
    }

    public int updateEvenementSportif(int id, String nom, int disciplineSportiveId) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == id) {
                evenementSportif.setNom(nom);
                evenementSportif.setDisciplineSportiveId(disciplineSportiveId);
                saveToJSON();
                return id;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + id);
    }

    public int addAthleteToEvenementSportif(int evenementSportifId, int athleteId) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == evenementSportifId) {
                evenementSportif.getAthletesId().add(athleteId);
                saveToJSON();
                return evenementSportifId;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + evenementSportifId);
    }

    public int removeAthleteFromEvenementSportif(int evenementSportifId, int athleteId) {
        for (EvenementSportif evenementSportif : evenementsSportifs) {
            if (evenementSportif.getId() == evenementSportifId) {
                evenementSportif.getAthletesId().remove((Integer) athleteId);
                saveToJSON();
                return evenementSportifId;
            }
        }
        throw new IllegalArgumentException("Evenement Sportif not found: " + evenementSportifId);
    }
}
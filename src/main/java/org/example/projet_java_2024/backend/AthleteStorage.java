package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

public class AthleteStorage {
    static final private String FILENAME = "/org/example/projet_java_2024/athletes.json";

    private final List<Athlete> athletes;

    public AthleteStorage() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = AthleteStorage.class.getResourceAsStream(FILENAME)) {
            if (is == null) {
                throw new RuntimeException("File not found: " + FILENAME);
            } else {
                this.athletes = mapper.readValue(is, new TypeReference<List<Athlete>>() {});
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading JSON file: " + FILENAME, e);
        }
    }

    public void saveToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileWriter writer = new FileWriter("src/main/resources" + FILENAME, false);
            mapper.writeValue(writer, athletes);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while writing JSON file: " + FILENAME, e);
        }
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

    public List<Athlete> getAthletesByProperty(String property, String value) {
        List<Athlete> result = new ArrayList<>();
        for (Athlete athlete : athletes) {
            switch (property) {
                case "nomAthlete":
                    if (athlete.getNomAthlete().equals(value)) {
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
        Athlete athlete = new Athlete(athletes.size() + 1,
                nomAthlete,
                sexe,
                pays,
                age,
                nbParticipation);

        athletes.add(athlete);

        saveToJSON();

        return athlete.getId();
    }

    // Please don't
    public void deleteAthlete(int id) {
        for (Athlete athlete : athletes) {
            if (athlete.getId() == id) {
                athletes.remove(athlete);
                return;
            }
        }

        saveToJSON();
    }

    public int updateAthlete(int id, String nomAthlete, String sexe, String pays, int age, int nbParticipation) {
        for (Athlete athlete : athletes) {
            if (athlete.getId() == id) {
                athlete.setNomAthlete(nomAthlete);
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
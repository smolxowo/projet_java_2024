package org.example.projet_java_2024.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

abstract public class DatabaseGestionnaire<T> {
    private final String FILENAME;
    protected List<T> items; // replace 'athletes' with a more generic term

    protected DatabaseGestionnaire(String filename) {
        FILENAME = filename;
        loadFromJSON();
    }

    abstract protected TypeReference<List<T>> getTypeReference();

    final protected void saveToJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileWriter writer = new FileWriter("src/main/resources" + FILENAME, false);
            mapper.writeValue(writer, items);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while writing JSON file: " + FILENAME, e);
        }
    }

    final protected void loadFromJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = DatabaseGestionnaire.class.getResourceAsStream(FILENAME)) {
            if (is == null) {
                throw new RuntimeException("File not found: " + FILENAME);
            } else {
                if (is.available() > 0) {
                    this.items = mapper.readValue(is, getTypeReference());
                } else {
                    this.items = new ArrayList<>();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading JSON file: " + FILENAME, e);
        }
    }
}
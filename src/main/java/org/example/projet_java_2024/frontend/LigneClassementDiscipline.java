package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LigneClassementDiscipline {
    private final SimpleStringProperty discipline;
    private final SimpleIntegerProperty or;
    private final SimpleIntegerProperty argent;
    private final SimpleIntegerProperty bronze;

    public LigneClassementDiscipline(String discipline, int or, int argent, int bronze) {
        this.discipline = new SimpleStringProperty(discipline);
        this.or = new SimpleIntegerProperty(or);
        this.argent = new SimpleIntegerProperty(argent);
        this.bronze = new SimpleIntegerProperty(bronze);
    }

    // Getters for the properties
    public String getDiscipline() {
        return discipline.get();
    }

    public int getOr() {
        return or.get();
    }

    public int getArgent() {
        return argent.get();
    }

    public int getBronze() {
        return bronze.get();
    }
}
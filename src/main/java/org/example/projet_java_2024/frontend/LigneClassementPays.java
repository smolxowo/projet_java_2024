package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LigneClassementPays {
    private final SimpleStringProperty pays;
    private final SimpleIntegerProperty or;
    private final SimpleIntegerProperty argent;
    private final SimpleIntegerProperty bronze;

    public LigneClassementPays(String pays, int or, int argent, int bronze) {
        this.pays = new SimpleStringProperty(pays);
        this.or = new SimpleIntegerProperty(or);
        this.argent = new SimpleIntegerProperty(argent);
        this.bronze = new SimpleIntegerProperty(bronze);
    }

    // Getters for the properties
    public String getPays() {
        return pays.get();
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
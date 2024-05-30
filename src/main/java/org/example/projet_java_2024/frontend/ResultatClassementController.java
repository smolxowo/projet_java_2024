package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ResultatClassementController extends ResultatController {
    @FXML
    private Tab tabPays, tabDiscipline;

    @FXML
    private TableView<Resultat> resultatsTableViewPays, resultatsTableViewDiscipline;

    @FXML
    private TableColumn<Resultat, String> athleteColumn, paysColumn, disciplineColumn, orDisciplineColumn, argentDisciplineColumn, bronzeDisciplineColumn;

    @FXML
    private TableColumn<Resultat, Integer> nbMedaillesColumn, orPaysColumn, argentPaysColumn, bronzePaysColumn;


    @FXML
    public void initialize() {
        // Initialize the columns of the TableView
        disciplineColumn.setCellValueFactory(cellData -> {
            int eventId = cellData.getValue().getEvenementSportifId();
            String eventName = EVENEMENT_GESTIONNAIRE.getEvenementSportifNamebyID(eventId);
            return new SimpleStringProperty(eventName);
        });
        athleteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(ATHLETE_GESTIONNAIRE.getAthleteNameById(cellData.getValue().getAthleteId())));
        paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
        orPaysColumn.setCellValueFactory(new PropertyValueFactory<>("or"));
        argentPaysColumn.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronzePaysColumn.setCellValueFactory(new PropertyValueFactory<>("bronze"));

        orDisciplineColumn.setCellValueFactory(new PropertyValueFactory<>("or"));
        argentDisciplineColumn.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronzeDisciplineColumn.setCellValueFactory(new PropertyValueFactory<>("bronze"));

        nbMedaillesColumn.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getId();
            String medaille = RESULTAT_GESTIONNAIRE.getMedailleById(id);
            int or = 0, argent = 0, bronze = 0;

            switch (medaille) {
                case "or":
                    or++;
                    break;
                case "argent":
                    argent++;
                    break;
                case "bronze":
                    bronze++;
                    break;
            }

            return new SimpleIntegerProperty(or + argent + bronze).asObject();
        });

        loadResultatClassement();
    }

    protected void loadResultatClassement() {
        if (resultatsTableViewPays != null) {
            resultatsTableViewPays.getItems().clear();
            Map<String, Map<String, Integer>> medalsByCountry = RESULTAT_GESTIONNAIRE.getMedalsByCountry();

            for (Map.Entry<String, Map<String, Integer>> entry : medalsByCountry.entrySet()) {
                String country = entry.getKey();
                Map<String, Integer> medals = entry.getValue();

                Resultat resultat = new Resultat();
                RESULTAT_GESTIONNAIRE.setPays(resultat, country);

                resultatsTableViewPays.getItems().add(resultat);
            }
        }

        if (resultatsTableViewDiscipline != null) {
            resultatsTableViewDiscipline.getItems().clear();
            Map<String, List<String>> medalistsByDiscipline = RESULTAT_GESTIONNAIRE.getMedalistsByDiscipline();

            for (Map.Entry<String, List<String>> entry : medalistsByDiscipline.entrySet()) {
                String discipline = entry.getKey();
                List<String> athletes = entry.getValue();

                for (String athlete : athletes) {
                    Resultat resultat = new Resultat();
                    RESULTAT_GESTIONNAIRE.setDiscipline(resultat, discipline);
                    RESULTAT_GESTIONNAIRE.setAthlete(resultat, athlete);

                    resultatsTableViewDiscipline.getItems().add(resultat);
                }
            }
        }
    }
}
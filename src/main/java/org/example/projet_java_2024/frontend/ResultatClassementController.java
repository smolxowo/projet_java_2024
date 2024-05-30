package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultatClassementController extends ResultatController {
    @FXML
    private Tab tabPays, tabDiscipline;

    @FXML
    private TableView<Resultat> resultatsTableViewPays, resultatsTableViewDiscipline;

    @FXML
    private TableColumn<Resultat, String> athleteColumn, paysColumn, disciplineColumn;

    @FXML
    private TableColumn<Resultat, Integer> nbMedaillesColumn, orPaysColumn, argentPaysColumn, bronzePaysColumn;

    @FXML
    public void initialize(){
        paysColumn.setCellValueFactory(cellData -> {
            int athleteId = cellData.getValue().getAthleteId();
            String athleteCountry = ATHLETE_GESTIONNAIRE.getAthleteById(athleteId).getPays();
            return new SimpleStringProperty(athleteCountry);
        });

        Map<String, Map<String, Integer>> medalCountByCountryAndType = RESULTAT_GESTIONNAIRE.getMedalCountByCountryAndType();

        // Assumons que les sous-colonnes sont nommées orColumn, argentColumn et bronzeColumn
        orPaysColumn.setCellValueFactory(cellData -> {
            int athleteId = cellData.getValue().getAthleteId();
            String athleteCountry = ATHLETE_GESTIONNAIRE.getAthleteById(athleteId).getPays();
            int goldCount = medalCountByCountryAndType.getOrDefault(athleteCountry, new HashMap<>()).getOrDefault("or", 0);
            return new SimpleObjectProperty<>(goldCount);
        });

        argentPaysColumn.setCellValueFactory(cellData -> {
            int athleteId = cellData.getValue().getAthleteId();
            String athleteCountry = ATHLETE_GESTIONNAIRE.getAthleteById(athleteId).getPays();
            int silverCount = medalCountByCountryAndType.getOrDefault(athleteCountry, new HashMap<>()).getOrDefault("argent", 0);
            return new SimpleObjectProperty<>(silverCount);
        });

        bronzePaysColumn.setCellValueFactory(cellData -> {
            int athleteId = cellData.getValue().getAthleteId();
            String athleteCountry = ATHLETE_GESTIONNAIRE.getAthleteById(athleteId).getPays();
            int bronzeCount = medalCountByCountryAndType.getOrDefault(athleteCountry, new HashMap<>()).getOrDefault("bronze", 0);
            return new SimpleObjectProperty<>(bronzeCount);
        });

        loadResultatClassement();
    }

    protected void loadResultatClassement(){
        if (resultatsTableViewPays != null && resultatsTableViewDiscipline != null){
            resultatsTableViewPays.getItems().clear();
            resultatsTableViewDiscipline.getItems().clear();

            List<Resultat> resultats = RESULTAT_GESTIONNAIRE.getAllResultats();
            resultatsTableViewPays.getItems().addAll(resultats);
            resultatsTableViewDiscipline.getItems().addAll(resultats);
        }
    }
}
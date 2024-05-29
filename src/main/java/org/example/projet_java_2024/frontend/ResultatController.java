package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.*;

import java.io.IOException;
import java.util.List;

public class ResultatController extends AccueilController{
    @FXML
    private Button ajouter, supprimer, classement;

    @FXML
    private TableView<Resultat> resultatsTableView;
    @FXML
    private TableColumn<Resultat, String> athleteColumn, eventColumn, dateColumn, medailleColumn;
    @FXML
    private TableColumn<Resultat, Integer> scoreColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        athleteColumn.setCellValueFactory(cellData -> {
            int athleteId = cellData.getValue().getAthleteId();
            String athleteName = ATHLETE_GESTIONNAIRE.getAthleteNameById(athleteId);
            return new SimpleStringProperty(athleteName);
        });
        eventColumn.setCellValueFactory(cellData -> {
            int eventId = cellData.getValue().getEvenementSportifId();
            String eventName = EVENEMENT_GESTIONNAIRE.getEvenementSportifNamebyID(eventId);
            return new SimpleStringProperty(eventName);
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        medailleColumn.setCellValueFactory(new PropertyValueFactory<>("medaille"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Charger les athlètes dans le TableView
        loadResultat();
    }

    protected void loadResultat() {
        if (resultatsTableView != null) {
            resultatsTableView.getItems().clear();
            List<Resultat> resultats = RESULTAT_GESTIONNAIRE.getAllResultats();
            resultatsTableView.getItems().addAll(resultats);
        }
    }

    public int ajoutResultat(int athleteId, int evenementSportifId, int score, String temps, String medaille) {
        int newResultatId = RESULTAT_GESTIONNAIRE.addResultat(athleteId, evenementSportifId, score, temps, medaille);
        return newResultatId;
    }

    public void supprResultat(Resultat resultat) {
        RESULTAT_GESTIONNAIRE.deleteResultat(resultat.getId());
    }

    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/ResultatAjoutScene.fxml", "Ajouter un résultat", e);
    }

    public void onClassementClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/ResultatClassementScene.fxml", "Classement", e);
    }

    public void onSupprClick(ActionEvent e) throws IOException {
        Resultat selectedResultat = resultatsTableView.getSelectionModel().getSelectedItem();
        supprResultat(selectedResultat);
        loadScene("/org/example/projet_java_2024/frontend/ResultatScene.fxml", "Résultat", e);
    }
}


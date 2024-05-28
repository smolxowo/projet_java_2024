package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.*;

import java.io.IOException;

public class ResultatController extends AccueilController{
    private ResultatGestionnaire resultatGestionnaire = new ResultatGestionnaire();

    @FXML
    private Button accueilMenuButton, athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;
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
            AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();
            Athlete athlete = athleteGestionnaire.getAthleteById(athleteId);
            return new SimpleStringProperty(athlete.getNom());
        });
        eventColumn.setCellValueFactory(cellData -> {
            int evenementSportifId = cellData.getValue().getEvenementSportifId();
            EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();
            EvenementSportif evenementSportif = evenementSportifGestionnaire.getEvenementSportifById(evenementSportifId);
            return new SimpleStringProperty(evenementSportif.getNom());
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        medailleColumn.setCellValueFactory(new PropertyValueFactory<>("Médaille"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));

        // Charger les athlètes dans le TableView
        loadResultat();
    }

    private void loadResultat() {
        resultatsTableView.getItems().clear();
        resultatsTableView.getItems().addAll();
    }

    public int ajoutResultat(int athleteId, int evenementSportifId, int score, String temps, String medaille) {
        int newResultatId = resultatGestionnaire.addResultat(athleteId, evenementSportifId, score, temps, medaille);
        return newResultatId;
    }

    public void supprResultat(Resultat resultat) {
        resultatGestionnaire.deleteResultat(resultat.getId());
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
    }
}


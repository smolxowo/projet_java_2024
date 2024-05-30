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
            String eventName = EVENEMENT_GESTIONNAIRE.getEvenementSportifNamebyId(eventId);
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

    public boolean validateFields(String athleteName, String eventName, String score, String date) {
        if (date.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs vides");
            alert.setContentText("Veuillez remplir la date au format jj-mm-aaaa");
            alert.showAndWait();
            return false;
        }

        // Vérifier si la date est au format jj-mm-aaaa
        if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Date invalide");
            alert.setContentText("La date doit être au format jj-mm-aaaa");
            alert.showAndWait();
            return false;
        }

        int scoreInt;
        try {
            scoreInt = Integer.parseInt(score);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Score invalide");
            alert.setContentText("Le score doit être un nombre entier");
            alert.showAndWait();
            return false;
        }

        if (scoreInt < 0 || scoreInt > 100000) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Score invalide");
            alert.setContentText("Le score doit être un nombre entier positif inférieur ou égal à 100000");
            alert.showAndWait();
            return false;
        }

        if (athleteName == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Athlète non sélectionné");
            alert.setContentText("Veuillez sélectionner un athlète");
            alert.showAndWait();
            return false;
        }

        if (eventName == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Événement non sélectionné");
            alert.setContentText("Veuillez sélectionner un événement");
            alert.showAndWait();
            return false;
        }

        return true;
    }
}

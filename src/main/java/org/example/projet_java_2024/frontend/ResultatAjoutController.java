package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.projet_java_2024.backend.*;

import java.io.IOException;
import java.util.List;

public class ResultatAjoutController extends ResultatController {

    @FXML
    private ComboBox<String> athleteSelect, eventSelect, medailleSelect;

    @FXML
    private Label scoreLabel, dateLabel;
    @FXML
    private TextField scoreField, dateField;
    @FXML
    private Button ajouterResultat;

    @FXML
    public void initialize() {
        // Initialisation des ComboBox
        List<Athlete> athletes = ATHLETE_GESTIONNAIRE.getAllAthletes();
        for (Athlete athlete : athletes) {
            athleteSelect.getItems().add(athlete.getNom());
        }

        athleteSelect.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            Athlete selectedAthlete = ATHLETE_GESTIONNAIRE.getAthleteByName(newValue);
            List<EvenementSportif> athleteEvents = EVENEMENT_GESTIONNAIRE.getEvenementsSportifsByAthleteId(selectedAthlete.getId());
            eventSelect.getItems().clear();
            for (EvenementSportif event : athleteEvents) {
                eventSelect.getItems().add(event.getNom());
            }
        });
        medailleSelect.getItems().addAll("Or", "Argent", "Bronze", "Aucune");
        dateField.setText("01-01-2024");
    }

    public void onAjouterResultatClick(ActionEvent e) throws IOException {
        String athleteName = athleteSelect.getSelectionModel().getSelectedItem();
        Athlete athlete = ATHLETE_GESTIONNAIRE.getAthleteByName(athleteName);

        String eventName = eventSelect.getSelectionModel().getSelectedItem();
        EvenementSportif event = EVENEMENT_GESTIONNAIRE.getEvenementSportifByName(eventName);

        int score = Integer.parseInt(scoreField.getText());
        String date = dateField.getText();
        String medaille = medailleSelect.getSelectionModel().getSelectedItem();

        ajoutResultat(athlete.getId(), event.getId(), score, date, medaille);
        loadScene("/org/example/projet_java_2024/frontend/ResultatScene.fxml", "Résultat", e);
    }
}

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
    AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();
    EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();
    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private ComboBox<String> athleteSelect, eventSelect, medailleSelect;

    @FXML
    private Label scoreLabel, dateLabel;
    @FXML
    private TextField scoreField, dateField;
    @FXML
    private Button ajouter;

    @FXML
    public void initialize() {
        // Initialisation des ComboBox
        List<Athlete> allAthlete = athleteGestionnaire.getAllAthletes();
        for (Athlete athlete : allAthlete) {
            athleteSelect.getItems().add(athlete.getNom());
        }

        List<EvenementSportif> allEvenementsSportifs = evenementSportifGestionnaire.getAllEvenementsSportifs();
        for (EvenementSportif event : allEvenementsSportifs) {
            eventSelect.getItems().add(event.getNom());
        }

        medailleSelect.getItems().addAll("Or", "Argent", "Bronze", "Aucune");
    }

    public void onAjouterClick(ActionEvent e) throws IOException {
        String athleteName = athleteSelect.getSelectionModel().getSelectedItem();
        Athlete athlete = athleteGestionnaire.getAthleteByName(athleteName);

        String eventName = eventSelect.getSelectionModel().getSelectedItem();
        EvenementSportif event = evenementSportifGestionnaire.getEvenementSportifByName(eventName);

        int score = Integer.parseInt(scoreField.getText());
        String date = dateField.getText();
        String medaille = medailleSelect.getSelectionModel().getSelectedItem();

        ajoutResultat(athlete.getId(), event.getId(), score, date, medaille);
    }
}

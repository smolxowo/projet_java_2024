package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;

public class AthleteAjoutController extends AccueilController {
    @FXML
    private SplitMenuButton athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;
    @FXML
    private MenuItem addAthleteMenuItem, deleteAthleteMenuItem, updateAthleteMenuItem;
    @FXML
    private MenuItem addDisciplineMenuItem, deleteDisciplineMenuItem, assignDisciplineMenuItem;
    @FXML
    private MenuItem addEventMenuItem, deleteEventMenuItem, assignEventMenuItem;
    @FXML
    private MenuItem addResultatMenuItem, deleteResultatMenuItem, classementResultatMenuItem;

    @FXML
    private Label nomLabel, sexeLabel, paysLabel, ageLabel, nbParticipLabel, successLabel;
    @FXML
    private TextField nomInput, sexeInput, paysInput, ageInput, nbParticipInput;

    @FXML
    private Button soumettre;
    @FXML
    private Button effacer;

    public void onSoumettreClick(ActionEvent e) throws IOException {
        AthleteController athleteController = new AthleteController();
        //On ajoute un nouvel Athlete
        athleteController.ajoutAthlete(nomInput.getText(), sexeInput.getText(), paysInput.getText(), Integer.parseInt(ageInput.getText()),Integer.parseInt(nbParticipInput.getText()));
        //Mettre à jour la liste des athlètes dans AthleteController
        athleteController.updateAthleteList();
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }

    public void onEffacerClick(ActionEvent e) throws IOException {
        nomInput.clear();
        sexeInput.clear();
        paysInput.clear();
        ageInput.clear();
        nbParticipInput.clear();
    }
}

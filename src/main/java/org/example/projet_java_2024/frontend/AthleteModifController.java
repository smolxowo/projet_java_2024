package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.projet_java_2024.backend.Athlete;

import java.io.IOException;

public class AthleteModifController extends AthleteController{
    AthleteController athleteController = new AthleteController();
    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private Label nomLabel, sexeLabel, paysLabel, ageLabel, nbParticipLabel, successLabel;
    @FXML
    private TextField nomInput, sexeInput, paysInput, ageInput, nbParticipInput;

    @FXML
    private Button Modifier;
    @FXML
    private Button Annuler;


    public void initialize() {
        // Récupérer l'athlète sélectionné depuis le contrôleur AthleteController
        Athlete selectedAthlete = athleteController.athleteTableView.getSelectionModel().getSelectedItem();

        // Définir les informations de l'athlète sélectionné comme placeholders
        nomInput.setPromptText(selectedAthlete.getNom());
        sexeInput.setPromptText(selectedAthlete.getSexe());
        paysInput.setPromptText(selectedAthlete.getPays());
        ageInput.setPromptText(String.valueOf(selectedAthlete.getAge()));
        nbParticipInput.setPromptText(String.valueOf(selectedAthlete.getNbParticipation()));
    }

    public void onModifierClick(ActionEvent e) throws IOException {
        athleteController.modifAthlete(nomInput.getText(), sexeInput.getText(), paysInput.getText(), Integer.parseInt(ageInput.getText()), Integer.parseInt(nbParticipInput.getText()));
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }

    public void onAnnulerClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }
}

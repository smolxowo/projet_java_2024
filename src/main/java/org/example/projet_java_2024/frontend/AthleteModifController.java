package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.projet_java_2024.backend.Athlete;

import java.io.IOException;

public class AthleteModifController extends AthleteController{
    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private Label nomLabel, sexeLabel, paysLabel, ageLabel, nbParticipLabel, successLabel;
    @FXML
    private TextField nomInput, sexeInput, paysInput, ageInput, nbParticipInput;

    @FXML
    private Button modifier;
    @FXML
    private Button annuler;

    public void initialize() {
        Athlete selectedAthlete = SELECTED_ATHLETE;

        // Définir les informations de l'athlète sélectionné dans les TextField
        if (selectedAthlete != null) {
            nomInput.setText(selectedAthlete.getNom());
            sexeInput.setText(selectedAthlete.getSexe());
            paysInput.setText(selectedAthlete.getPays());
            ageInput.setText(String.valueOf(selectedAthlete.getAge()));
            nbParticipInput.setText(String.valueOf(selectedAthlete.getNbParticipation()));
        }
    }

    public void onModifierClick(ActionEvent e) throws IOException {
        modifAthlete(nomInput.getText(), sexeInput.getText(), paysInput.getText(), Integer.parseInt(ageInput.getText()), Integer.parseInt(nbParticipInput.getText()));
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }

    public void onAnnulerClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }
}

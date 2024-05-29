package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AthleteAjoutController extends AthleteController {
    @FXML private TextField nomInput, sexeInput, paysInput, ageInput, nbParticipInput;

    @FXML public void initialize() {
        // Keep it empty to overwrite base class
    }

    public void onSoumettreClick(ActionEvent e) throws IOException {
        String nom = nomInput.getText().trim();
        String sexe = sexeInput.getText().trim();
        String pays = paysInput.getText().trim();
        String ageStr = ageInput.getText().trim();
        String nbParticipStr = nbParticipInput.getText().trim();

        if (validateFields(nom, sexe, pays, ageStr, nbParticipStr)) {
            int age = Integer.parseInt(ageStr);
            int nbParticip = Integer.parseInt(nbParticipStr);
            ajoutAthlete(nom, sexe, pays, age, nbParticip);
            loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
        }
    }

    public void onEffacerClick(ActionEvent e) throws IOException {
        nomInput.clear();
        sexeInput.clear();
        paysInput.clear();
        ageInput.clear();
        nbParticipInput.clear();
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }
}

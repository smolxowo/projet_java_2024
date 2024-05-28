package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.io.IOException;

public class AthleteAjoutController extends AthleteController {
    @FXML private TextField nomInput;
    @FXML private TextField sexeInput;
    @FXML private TextField paysInput;
    @FXML private TextField ageInput;
    @FXML private TextField nbParticipInput;

    @FXML private Button soumettre;
    @FXML private Button effacer;

    @FXML public void initialize() {
        // Keep it empty to overwrite base class
    }

    public void onSoumettreClick(ActionEvent e) throws IOException {
        ajoutAthlete(
                nomInput.getText(),
                sexeInput.getText(),
                paysInput.getText(),
                Integer.parseInt(ageInput.getText()),
                Integer.parseInt(nbParticipInput.getText())
        );
        // wait for 1 second
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
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

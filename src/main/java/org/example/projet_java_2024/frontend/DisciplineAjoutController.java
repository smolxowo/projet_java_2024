package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DisciplineAjoutController extends DisciplineController {
    @FXML
    private TextField nomInput;

    public void onSoumettreClick(ActionEvent e) throws IOException {
        String nomDiscipline = nomInput.getText().trim();

        if (validateFields(nomDiscipline)) {
            ajoutDiscipline(nomInput.getText());
            loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline Sportive", e);
        }
    }

    public void onEffacerClick(ActionEvent e) throws IOException {
        nomInput.clear();
        loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline Sportive", e);
    }
}

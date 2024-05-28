package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DisciplineAjoutController extends DisciplineController {

    @FXML
    private Label nomLabel;
    @FXML
    private TextField nomInput;

    @FXML
    private Button soumettre;
    @FXML
    private Button effacer;

    public void onSoumettreClick(ActionEvent e) throws IOException {
        //On ajoute une nouvelle Discipline
        ajoutDiscipline(nomInput.getText());
        loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline Sportive", e);
    }

    public void onEffacerClick(ActionEvent e) throws IOException {
        nomInput.clear();
    }
}

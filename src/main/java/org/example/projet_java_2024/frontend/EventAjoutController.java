package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;

import java.io.IOException;
import java.util.List;

public class EventAjoutController extends EventController{
    //Faire un select de discipline et un input du nom
    DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();
    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private Label nomLabel;
    @FXML
    private TextField nomInput;

    @FXML
    private Button ajouter;

    @FXML
    private ComboBox<String> disciplineSelect;

    @FXML
    public void initialize() {
        List<DisciplineSportive> allDisciplines = disciplineSportiveGestionnaire.getAllDisciplinesSportives();
        for (DisciplineSportive discipline : allDisciplines) {
            disciplineSelect.getItems().add(discipline.getNom());
        }
    }

    public void onAjouterClick(ActionEvent e) throws IOException {
        String nom = nomInput.getText();
        String selectedDisciplineName = disciplineSelect.getSelectionModel().getSelectedItem();
        DisciplineSportive selectedDiscipline = disciplineSportiveGestionnaire.getDisciplineSportiveByName(selectedDisciplineName);
        ajoutEvent(nom, selectedDiscipline.getId());
        loadScene("/org/example/projet_java_2024/frontend/EventScene.fxml", "Evènement", e);
    }
}

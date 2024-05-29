package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.projet_java_2024.backend.DisciplineSportive;

import java.io.IOException;
import java.util.List;

public class EventAjoutController extends EventController{
    @FXML
    private TextField nomInput;

    @FXML
    private ComboBox<String> disciplineSelect;

    @FXML
    public void initialize() {
        List<DisciplineSportive> allDisciplines = DISCIPLINE_GESTIONNAIRE.getAllDisciplinesSportives();
        for (DisciplineSportive discipline : allDisciplines) {
            disciplineSelect.getItems().add(discipline.getNom());
        }
    }

    public void onSoumettreClick(ActionEvent e) throws IOException {
        String nomEvenement = nomInput.getText().trim();

        if (validateFields(nomEvenement)) {
            String selectedDisciplineName = disciplineSelect.getSelectionModel().getSelectedItem();
            DisciplineSportive selectedDiscipline = DISCIPLINE_GESTIONNAIRE.getDisciplineSportiveByName(selectedDisciplineName);
            ajoutEvent(nomEvenement, selectedDiscipline.getId());
            loadScene("/org/example/projet_java_2024/frontend/EventScene.fxml", "Evènement", e);
        }
    }
}

package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineController extends AccueilController {
    @FXML
    protected Button ajouter, assign, supprimer;

    @FXML
    protected TableView<DisciplineSportive> disciplineTableView;
    @FXML
    protected TableColumn<DisciplineSportive, String> nomColumn, participantColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        if (nomColumn != null && participantColumn != null) {
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            participantColumn.setCellValueFactory(cellData -> {
                List<Integer> participantIds = cellData.getValue().getParticipantId();
                List<String> participantNames = new ArrayList<>();
                for (Integer id : participantIds) {
                    String name = athleteGestionnaire.getAthleteNameById(id);
                    participantNames.add(name);
                }
                return new SimpleStringProperty(String.join(", ", participantNames));
            });
        }

        // Charger les disciplines dans le TableView
        loadDiscipline();
    }

    protected void loadDiscipline() {
        if (disciplineTableView != null) {
            disciplineTableView.getItems().clear();
            List<DisciplineSportive> disciplines = disciplineSportiveGestionnaire.getAllDisciplinesSportives();
            disciplineTableView.getItems().addAll(disciplines);
        }
    }

    public int ajoutDiscipline(String nom) {
        List<Integer> participantId = new ArrayList<>();
        int newDisciplineId = disciplineSportiveGestionnaire.addDisciplineSportive(nom, participantId);
        return newDisciplineId;
    }

    public void supprDiscipline(DisciplineSportive disciplineSportive) {
        disciplineSportiveGestionnaire.deleteDisciplineSportif(disciplineSportive.getId());
    }

    public int ajoutParticipant(int disciplineSportifId, int participantId) {
        int newParticipantId = disciplineSportiveGestionnaire.addParticipantToDisciplineSportif(disciplineSportifId, participantId);
        return newParticipantId;
    }

    public int removeParticipant(int disciplineSportifId, int participantId) {
        int newParticipantId = disciplineSportiveGestionnaire.removeParticipantFromDisciplineSportif(disciplineSportifId, participantId);
        return newParticipantId;
    }

    @FXML
    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineAjoutScene.fxml", "Ajouter une discipline", e);
    }

    @FXML
    public void onSupprClick(ActionEvent e) throws IOException {
        DisciplineSportive selectedDiscipline = disciplineTableView.getSelectionModel().getSelectedItem();
        if (selectedDiscipline != null) {
            supprDiscipline(selectedDiscipline);
            loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline", e);
        }
    }

    @FXML
    public void onAssignClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineAssignScene.fxml", "Ajouter ou supprimer des participants", e);
    }
}

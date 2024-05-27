package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.Athlete;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineController extends AccueilController {
    private DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();

    @FXML
    private Button accueilMenuButton, athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;
    @FXML
    private Button ajouter, assign, supprimer;

    @FXML
    private TableView<DisciplineSportive> disciplineTableView;
    @FXML
    private TableColumn<DisciplineSportive, String> nomColumn, participantColumn;

    @FXML
    private Button ajouterButton, supprButton, assignButton;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("Discipline"));
        participantColumn.setCellValueFactory(new PropertyValueFactory<>("Participants"));

        // Charger les athlètes dans le TableView
        loadDiscipline();
    }

    private void loadDiscipline() {
        disciplineTableView.getItems().clear();
        disciplineTableView.getItems().addAll();
    }

    public int ajoutDiscipline(String nom) {
        List<Integer> participantId = new ArrayList<>();
        int newDisciplineId = disciplineSportiveGestionnaire.addDisciplineSportive(nom,participantId);
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


    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineAjoutScene.fxml", "Ajouter une discipline", e);
    }

    public void onSupprClick(ActionEvent e) throws IOException {
        DisciplineSportive selectedDiscipline = disciplineTableView.getSelectionModel().getSelectedItem();
        supprDiscipline(selectedDiscipline);
    }

    public void onAssignClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineAssignScene.fxml", "", e);
    }
}
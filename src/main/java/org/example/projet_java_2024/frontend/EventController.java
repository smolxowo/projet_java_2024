package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.EvenementSportif;
import org.example.projet_java_2024.backend.EvenementSportifGestionnaire;

import java.io.IOException;

public class EventController extends AccueilController {
    private EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();

    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private Button ajouterButton, supprButton, assignButton;

    @FXML
    private TableView<EvenementSportif> eventTableView;
    @FXML
    private TableColumn<EvenementSportif, String> eventColumn, participantColumn;
    @FXML
    private TableColumn<DisciplineSportive, String> disciplineColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        disciplineColumn.setCellValueFactory(new PropertyValueFactory<>("Discipline"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("Evènement"));
        participantColumn.setCellValueFactory(new PropertyValueFactory<>("Participants"));

        // Charger les athlètes dans le TableView
        loadDiscipline();
    }

    private void loadDiscipline() {
        eventTableView.getItems().clear();
        eventTableView.getItems().addAll();
    }


    public int ajoutEvent(String nom, int disciplineSportiveId){
        int newEventId = evenementSportifGestionnaire.addEvenementSportif(nom, disciplineSportiveId);
        return newEventId;
    }

    public void supprEvent(EvenementSportif evenementSportif) {
        evenementSportifGestionnaire.deleteEvenementSportif(evenementSportif.getId());
    }

    public int ajoutParticipant(int evenementSportifId, int participantId) {
        int newParticipantId = evenementSportifGestionnaire.addParticipantToEvenementSportif(evenementSportifId, participantId);
        return newParticipantId;
    }

    public int removeParticipant(int evenementSportifId, int participantId) {
        int newParticipantId = evenementSportifGestionnaire.removeParticipantFromEvenementSportif(evenementSportifId, participantId);
        return newParticipantId;
    }

    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/EventAjoutScene.fxml", "Ajouter un évènement", e);
    }

    public void onSupprClick(ActionEvent e) throws IOException {
        EvenementSportif selectedEvent = eventTableView.getSelectionModel().getSelectedItem();
        supprEvent(selectedEvent);
    }

    public void onAssignClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/EventAssignScene.fxml", "Assigner un évènement", e);
    }

}
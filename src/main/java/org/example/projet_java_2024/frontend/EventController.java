package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.EvenementSportif;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventController extends AccueilController {
    @FXML
    private Button accueilMenuButton, athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;
    @FXML
    private Button ajouter, assign, supprimer;

    @FXML
    protected TableView<EvenementSportif> eventTableView;
    @FXML
    protected TableColumn<EvenementSportif, String> eventColumn, athleteColumn;
    @FXML
    protected TableColumn<EvenementSportif, String> disciplineColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        disciplineColumn.setCellValueFactory(cellData -> {
            int disciplineId = cellData.getValue().getDisciplineSportiveId();
            String disciplineName = DISCIPLINE_GESTIONNAIRE.getDisciplineNameById(disciplineId);
            return new SimpleStringProperty(disciplineName);
        });
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        athleteColumn.setCellValueFactory(cellData -> {
            List<Integer> athletesId = cellData.getValue().getAthletesId();
            List<String> athletesNames = new ArrayList<>();
            for (Integer id : athletesId) {
                String name = ATHLETE_GESTIONNAIRE.getAthleteNameById(id);
                athletesNames.add(name);
            }
            return new SimpleStringProperty(String.join(", ", athletesNames));
        });

        // Charger les évènements dans le TableView
        loadEvenements();
    }

    protected void loadEvenements() {
        if (eventTableView != null) {
            eventTableView.getItems().clear();
            List<EvenementSportif> evenements = EVENEMENT_GESTIONNAIRE.getAllEvenementsSportifs();
            eventTableView.getItems().addAll(evenements);
        }
    }


    public int ajoutEvent(String nom, int disciplineSportiveId){
        int newEventId = EVENEMENT_GESTIONNAIRE.addEvenementSportif(nom, disciplineSportiveId);
        return newEventId;
    }

    public void supprEvent(EvenementSportif evenementSportif) {
        EVENEMENT_GESTIONNAIRE.deleteEvenementSportif(evenementSportif.getId());
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
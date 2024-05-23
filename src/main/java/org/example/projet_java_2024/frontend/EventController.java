package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.EvenementSportif;
import org.example.projet_java_2024.backend.EvenementSportifGestionnaire;

public class EventController extends AccueilController {
    private EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();

    @FXML
    private SplitMenuButton athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;
    @FXML
    private MenuItem addAthleteMenuItem, deleteAthleteMenuItem, updateAthleteMenuItem;
    @FXML
    private MenuItem addDisciplineMenuItem, deleteDisciplineMenuItem, assignDisciplineMenuItem;
    @FXML
    private MenuItem addEventMenuItem, deleteEventMenuItem, assignEventMenuItem;
    @FXML
    private MenuItem addResultatMenuItem, deleteResultatMenuItem, classementResultatMenuItem;

    @FXML
    private TableView<EvenementSportif> eventTableView;
    @FXML
    private TableColumn<EvenementSportif, String> nomColumn, participantColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("Evènement"));
        participantColumn.setCellValueFactory(new PropertyValueFactory<>("Participants"));

        // Charger les athlètes dans le TableView
        loadDiscipline();
    }

    private void loadDiscipline() {
        eventTableView.getItems().clear();
        eventTableView.getItems().addAll();
    }

    public void updateDisciplineList() {
        loadDiscipline();
    }

    public int ajoutDiscipline(String nom){
        int newEventId = evenementSportifGestionnaire.addEvenementSportif(nom);
        return newEventId;
    }
}
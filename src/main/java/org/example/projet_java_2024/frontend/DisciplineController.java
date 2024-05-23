package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;

public class DisciplineController extends AccueilController {
    private DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();

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
    private TableView<DisciplineSportive> disciplineTableView;
    @FXML
    private TableColumn<DisciplineSportive, String> nomColumn, participantColumn;

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

    public void updateDisciplineList() {
        loadDiscipline();
    }

    public int ajoutDiscipline(String nom){
        int newDisciplineId = disciplineSportiveGestionnaire.addDisciplineSportif(nom);
        return newDisciplineId;
    }
}
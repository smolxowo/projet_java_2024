package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.EvenementSportif;
import org.example.projet_java_2024.backend.EvenementSportifGestionnaire;
import org.example.projet_java_2024.backend.Resultat;
import org.example.projet_java_2024.backend.ResultatGestionnaire;

public class ResultatsController extends AccueilController {
    private ResultatGestionnaire resultatGestionnaire = new ResultatGestionnaire();

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
    private TableView<Resultat> resultatsTableView;
    @FXML
    private TableColumn<Resultat, String> athleteColumn, eventColumn, dateColumn, medailleColumn;
    @FXML
    private TableColumn<Resultat, Integer> scoreColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        athleteColumn.setCellValueFactory(new PropertyValueFactory<>("Athlète"));
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("Evènement"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        medailleColumn.setCellValueFactory(new PropertyValueFactory<>("Médaille"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));

        // Charger les athlètes dans le TableView
        loadResultat();
    }

    private void loadResultat() {
        resultatsTableView.getItems().clear();
        resultatsTableView.getItems().addAll();
    }

    public void updateResultatList() {
        loadResultat();
    }

}
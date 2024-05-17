package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.Athlete;
import org.example.projet_java_2024.backend.AthleteGestionnaire;

public class AthleteController {
    private AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();

    @FXML
    private TableView<Athlete> athleteTableView;
    @FXML
    private TableColumn<Athlete, String> nomColumn, sexeColumn, paysColumn;
    @FXML
    private TableColumn<Athlete, Integer> IDColumn, ageColumn, participationColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        participationColumn.setCellValueFactory(new PropertyValueFactory<>("nbParticipation"));

        // Charger les athlètes dans le TableView
        loadAthletes();
    }

    private void loadAthletes() {
        athleteTableView.getItems().clear();
        athleteTableView.getItems().addAll(athleteGestionnaire.getAllAthletes());
    }
}
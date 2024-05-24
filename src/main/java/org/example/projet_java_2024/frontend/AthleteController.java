package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.Athlete;
import org.example.projet_java_2024.backend.AthleteGestionnaire;

import java.io.IOException;

public class AthleteController extends AccueilController {
    private AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();

    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;


    @FXML
    protected TableView<Athlete> athleteTableView;
    @FXML
    private TableColumn<Athlete, String> nomColumn, sexeColumn, paysColumn;
    @FXML
    private TableColumn<Athlete, Integer> ageColumn, participationColumn;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
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

    public int ajoutAthlete(String nom, String sexe, String pays, int age, int nbParticip) {
        int newAthleteId = athleteGestionnaire.addAthlete(nom, sexe, pays, age, nbParticip);
        return newAthleteId;
    }

    public void supprAthlete(Athlete athlete) {
        athleteGestionnaire.deleteAthlete(athlete.getId());
    }

    public int modifAthlete(String nom, String sexe, String pays, int age, int nbParticip) {
        Athlete selectedAthlete = athleteTableView.getSelectionModel().getSelectedItem();
        int id = selectedAthlete.getId();
        int newAthleteId = athleteGestionnaire.updateAthlete(id, nom, sexe, pays, age, nbParticip);
        return newAthleteId;
    }

    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteAjoutScene.fxml", "Ajouter un athlète", e);
    }

    public void onSupprClick(ActionEvent e) throws IOException {
        Athlete selectedAthlete = athleteTableView.getSelectionModel().getSelectedItem();
        supprAthlete(selectedAthlete);
    }

    public void onModifClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteModifScene.fxml", "Modifier un athlète", e);
    }
}
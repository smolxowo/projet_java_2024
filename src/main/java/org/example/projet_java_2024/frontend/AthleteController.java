package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.Athlete;
import java.io.IOException;

public class AthleteController extends AccueilController {
    @FXML protected Button ajouter, modifier, supprimer;

    @FXML
    protected TableView<Athlete> athleteTableView;
    @FXML
    protected TableColumn<Athlete, String> nomColumn, sexeColumn, paysColumn;
    @FXML
    protected TableColumn<Athlete, Integer> ageColumn, participationColumn;

    protected static Athlete SELECTED_ATHLETE = null;

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

    public void loadAthletes() {
        athleteTableView.getItems().clear();
        athleteTableView.getItems().addAll(ATHLETE_GESTIONNAIRE.getAllAthletes());
    }

    public int ajoutAthlete(String nom, String sexe, String pays, int age, int nbParticip) {
        int newAthleteId = ATHLETE_GESTIONNAIRE.addAthlete(nom, sexe, pays, age, nbParticip);
        return newAthleteId;
    }

    public void supprAthlete(Athlete athlete) {
        ATHLETE_GESTIONNAIRE.deleteAthlete(athlete.getId());
    }

    public int modifAthlete(String nom, String sexe, String pays, int age, int nbParticip) {
        Athlete selectedAthlete = SELECTED_ATHLETE;
        int id = selectedAthlete.getId();
        int newAthleteId = ATHLETE_GESTIONNAIRE.updateAthlete(id, nom, sexe, pays, age, nbParticip);
        return newAthleteId;
    }

    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteAjoutScene.fxml", "Ajouter un athlète", e);
    }

    public void onSupprClick(ActionEvent e) throws IOException {
        Athlete selectedAthlete = athleteTableView.getSelectionModel().getSelectedItem();
        supprAthlete(selectedAthlete);
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }

    public void onModifClick(ActionEvent e) throws IOException {
        Athlete selectedAthlete = athleteTableView.getSelectionModel().getSelectedItem();
        if (selectedAthlete == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un athlète à modifier.");
            alert.showAndWait();
        } else {
            SELECTED_ATHLETE = selectedAthlete;
            loadScene("/org/example/projet_java_2024/frontend/AthleteModifScene.fxml", "Modifier un athlète", e);
        }
    }
}

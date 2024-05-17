package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.projet_java_2024.backend.AthleteGestionnaire;

import java.io.IOException;

public class AthleteAjoutController {
    @FXML
    private Label nomLabel, sexeLabel, paysLabel, ageLabel, nbParticipLabel, successLabel;
    @FXML
    private TextField nomInput, sexeInput, paysInput, ageInput, nbParticipInput;

    @FXML
    private Button soumettre;
    @FXML
    private Button effacer;

    public void onSoumettreClick(ActionEvent e) throws IOException {
        //Chargement de AthleteScene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/projet_java_2024/frontend/AthleteScene.fxml"));
        Parent root = loader.load();
        //Obtention du contrôleur de AthleteScene
        AthleteController athleteController = loader.getController();

        //On ajoute un nouvel Athlete
        ajoutAthlete(nomInput.getText(), sexeInput.getText(), paysInput.getText(), Integer.parseInt(ageInput.getText()),Integer.parseInt(nbParticipInput.getText()));

        //show AthleteScene in new stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Athlete");
        stage.show();
        //Fermeture de AthleteAjoutController
        Node source=(Node) e.getSource();
        Stage s = (Stage) source.getScene().getWindow();
        s.close();
    }

    public int ajoutAthlete(String nom, String sexe, String pays, int age, int nbParticip){
        AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();
        int newAthleteId = athleteGestionnaire.addAthlete(nom,sexe,pays,age,nbParticip);
        return newAthleteId;
    }

    public void onEffacerClick(ActionEvent e) throws IOException {
        nomInput.clear();
        sexeInput.clear();
        paysInput.clear();
        ageInput.clear();
        nbParticipInput.clear();
    }
}

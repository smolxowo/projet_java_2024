package org.example.projet_java_2024.frontend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.example.projet_java_2024.backend.AthleteGestionnaire;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;
import org.example.projet_java_2024.backend.EvenementSportifGestionnaire;
import org.example.projet_java_2024.backend.ResultatGestionnaire;

import java.io.IOException;

public class AccueilController {
    protected static AthleteGestionnaire ATHLETE_GESTIONNAIRE = new AthleteGestionnaire();
    protected static DisciplineSportiveGestionnaire DISCIPLINE_GESTIONNAIRE = new DisciplineSportiveGestionnaire();
    protected static EvenementSportifGestionnaire EVENEMENT_GESTIONNAIRE = new EvenementSportifGestionnaire();
    protected static ResultatGestionnaire RESULTAT_GESTIONNAIRE = new ResultatGestionnaire();

    @FXML
    protected Button accueilMenuButton, athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    public void loadScene(String fxmlFile, String title, ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
        // Fermeture de la scène précédente
        Object source = e.getSource();
        if (source instanceof Node) {
            Stage previousStage = (Stage) ((Node) source).getScene().getWindow();
            previousStage.close();
        } else if (source instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) source;
            Scene menuItemScene = menuItem.getParentPopup().getOwnerWindow().getScene();
            Stage previousStage = (Stage) menuItemScene.getWindow();
            previousStage.close();
        }
    }

    @FXML
    public void onAccueilMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AccueilScene.fxml", "Accueil", e);
    }

    @FXML
    public void onAthleteMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }

    @FXML
    public void onDisciplineMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline", e);
    }

    @FXML
    public void onEventMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/EventScene.fxml", "Evènement", e);
    }

    @FXML
    public void onResultatsMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/ResultatScene.fxml", "Résultats", e);
    }
}


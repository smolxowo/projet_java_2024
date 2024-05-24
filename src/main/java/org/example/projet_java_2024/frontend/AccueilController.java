package org.example.projet_java_2024.frontend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {
    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;


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

    public void onAthleteMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteScene.fxml", "Athlete", e);
    }

    public void onDisciplineMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline", e);
    }

    public void onEventMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/EventScene.fxml", "Evènement", e);
    }

    public void onResultatsMenuButtonClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/ResultatsScene.fxml", "Résultats", e);
    }

    public void onAddAthleteMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AthleteAjoutScene.fxml", "Ajouter un athlète", e);
    }

    public void onDeleteAthleteMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DeleteAthleteScene.fxml", "Supprimer un athlète", e);
    }

    public void onUpdateAthleteMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/UpdateAthleteScene.fxml", "Modifier un athlète", e);
    }

    public void onAddDisciplineMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AddDisciplineScene.fxml", "Ajouter une discipline", e);
    }

    public void onDeleteDisciplineMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DeleteDisciplineScene.fxml", "Supprimer une discipline", e);
    }

    public void onAssignDisciplineMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AssignDisciplineScene.fxml", "Assigner une discipline", e);
    }

    public void onAddEventMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AddEventScene.fxml", "Ajouter un évènement", e);
    }

    public void onDeleteEventMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DeleteEventScene.fxml", "Supprimer un évènement", e);
    }

    public void onAssignEventMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AssignEventScene.fxml", "Assigner un évènement", e);
    }

    public void onAddResultatMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/AddResultatScene.fxml", "Ajouter un résultat", e);
    }

    public void onDeleteResultatMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DeleteResultatScene.fxml", "Supprimer un résultat", e);
    }

    public void onClassementResultatMenuItemClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/ClassementResultatScene.fxml", "Classement des résultats", e);
    }
}


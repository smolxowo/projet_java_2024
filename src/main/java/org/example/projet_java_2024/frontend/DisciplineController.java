package org.example.projet_java_2024.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.projet_java_2024.backend.DisciplineSportive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineController extends AccueilController {
    @FXML
    protected TableView<DisciplineSportive> disciplineTableView;
    @FXML
    protected TableColumn<DisciplineSportive, String> nomColumn, athleteColumn;

    protected static DisciplineSportive SELECTED_DISCIPLINE = null;

    @FXML
    public void initialize() {
        // Initialisation des colonnes du TableView
        if (nomColumn != null && athleteColumn != null) {
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            athleteColumn.setCellValueFactory(cellData -> {
                List<Integer> athletesId = cellData.getValue().getAthletesId();
                List<String> athletesNames = new ArrayList<>();
                for (Integer id : athletesId) {
                    String name = ATHLETE_GESTIONNAIRE.getAthleteNameById(id);
                    athletesNames.add(name);
                }
                return new SimpleStringProperty(String.join(", ", athletesNames));
            });
        }

        // Charger les disciplines dans le TableView
        loadDiscipline();
    }

    protected void loadDiscipline() {
        if (disciplineTableView != null) {
            disciplineTableView.getItems().clear();
            List<DisciplineSportive> disciplines = DISCIPLINE_GESTIONNAIRE.getAllDisciplinesSportives();
            disciplineTableView.getItems().addAll(disciplines);
        }
    }

    public int ajoutDiscipline(String nom) {
        List<Integer> athletesId = new ArrayList<>();
        int newDisciplineId = DISCIPLINE_GESTIONNAIRE.addDisciplineSportive(nom, athletesId);
        return newDisciplineId;
    }

    public void supprDiscipline(DisciplineSportive disciplineSportive) {
        DISCIPLINE_GESTIONNAIRE.deleteDisciplineSportive(disciplineSportive.getId());
    }

    @FXML
    public void onAjouterClick(ActionEvent e) throws IOException {
        loadScene("/org/example/projet_java_2024/frontend/DisciplineAjoutScene.fxml", "Ajouter une discipline", e);
    }

    @FXML
    public void onSupprClick(ActionEvent e) throws IOException {
        DisciplineSportive selectedDiscipline = disciplineTableView.getSelectionModel().getSelectedItem();
        if (selectedDiscipline == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune discipline sélectionnée");
            alert.setContentText("Veuillez sélectionner une discipline à supprimer");
            alert.showAndWait();
        } else {
            supprDiscipline(selectedDiscipline);
            loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline", e);
        }
    }

    @FXML
    public void onAssignClick(ActionEvent e) throws IOException {
        DisciplineSportive selectedDiscipline = disciplineTableView.getSelectionModel().getSelectedItem();
        if (selectedDiscipline != null) {
            SELECTED_DISCIPLINE = selectedDiscipline;
            loadScene("/org/example/projet_java_2024/frontend/DisciplineAssignScene.fxml", "Ajouter ou supprimer des athlètes", e);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune discipline sélectionnée");
            alert.setContentText("Veuillez sélectionner une discipline pour ajouter ou supprimer des athlètes");
            alert.showAndWait();
        }
    }

    protected boolean validateFields(String nomDiscipline) {
        if (nomDiscipline.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champ vide");
            alert.setContentText("Veuillez remplir le nom de la discipline");
            alert.showAndWait();
            return false;
        }

        return true;
    }
}

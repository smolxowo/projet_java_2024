package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.example.projet_java_2024.backend.Athlete;
import org.example.projet_java_2024.backend.AthleteGestionnaire;
import org.example.projet_java_2024.backend.DisciplineSportive;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineAssignController extends DisciplineController {
    //Faire un select des disciplines et une checklist des participants
    DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();
    AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();

    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private ComboBox<String> disciplineSelect;

    @FXML
    private Button assigner;
    @FXML
    private Button remove;

    @FXML
    private List<CheckBox> participantChecks = new ArrayList<>(); // Liste pour stocker vos CheckBox

    @FXML
    public void initialize() {
        List<DisciplineSportive> allDisciplines = disciplineSportiveGestionnaire.getAllDisciplinesSportives();
        for (DisciplineSportive discipline : allDisciplines) {
            disciplineSelect.getItems().add(discipline.getNom());
        }
        List<Athlete> allAthletes = athleteGestionnaire.getAllAthletes();
        for (Athlete athlete : allAthletes) {
            CheckBox checkBox = new CheckBox(athlete.getNom());
            participantChecks.add(checkBox); // Ajoute chaque CheckBox à la liste
        }
    }

        public void onAssignerClick(ActionEvent e) throws IOException {
            String selectedDisciplineName = disciplineSelect.getSelectionModel().getSelectedItem();
            DisciplineSportive selectedDiscipline = disciplineSportiveGestionnaire.getDisciplineSportiveByName(selectedDisciplineName);

            for (CheckBox checkBox : participantChecks) {
                if (checkBox.isSelected()) {
                    String athleteName = checkBox.getText();
                    Athlete selectedAthlete = athleteGestionnaire.getAthleteByName(athleteName);
                    ajoutParticipant(selectedDiscipline.getId(), selectedAthlete.getId());
                }
            }
        }

        public void onRemoveClick(ActionEvent e) throws IOException {
            String selectedDisciplineName = disciplineSelect.getSelectionModel().getSelectedItem();
            DisciplineSportive selectedDiscipline = disciplineSportiveGestionnaire.getDisciplineSportiveByName(selectedDisciplineName);

            for (CheckBox checkBox : participantChecks) {
                if (checkBox.isSelected()) {
                    String athleteName = checkBox.getText();
                    Athlete selectedAthlete = athleteGestionnaire.getAthleteByName(athleteName);
                    removeParticipant(selectedDiscipline.getId(), selectedAthlete.getId());
                }
            }
        }
}







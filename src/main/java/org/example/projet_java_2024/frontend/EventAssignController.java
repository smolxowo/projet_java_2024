package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import org.example.projet_java_2024.backend.*;

import java.util.ArrayList;
import java.util.List;

public class EventAssignController {
    //Faire une checklist des participants et un select des disciplines puis évènements
    DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();
    AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();
    EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();

    @FXML
    private Button athleteMenuButton, disciplineMenuButton, eventMenuButton, resultatsMenuButton;

    @FXML
    private ComboBox<String> disciplineSelect, eventSelect;

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
        List<EvenementSportif> allEvents = evenementSportifGestionnaire.getAllEvenementsSportifs();
        for (EvenementSportif event : allEvents) {
            eventSelect.getItems().add(event.getNom());
        }
        List<Athlete> allAthletes = athleteGestionnaire.getAllAthletes();
        for (Athlete athlete : allAthletes) {
            CheckBox checkBox = new CheckBox(athlete.getNom());
            participantChecks.add(checkBox); // Ajoute chaque CheckBox à la liste
        }
    }

    public void onAssignerClick() {
        String selectedDisciplineName = disciplineSelect.getSelectionModel().getSelectedItem();
        DisciplineSportive selectedDiscipline = disciplineSportiveGestionnaire.getDisciplineSportiveByName(selectedDisciplineName);
        String selectedEventName = eventSelect.getSelectionModel().getSelectedItem();
        EvenementSportif selectedEvent = evenementSportifGestionnaire.getEvenementSportifByName(selectedEventName);

        for (CheckBox checkBox : participantChecks) {
            if (checkBox.isSelected()) {
                String athleteName = checkBox.getText();
                Athlete selectedAthlete = athleteGestionnaire.getAthleteByName(athleteName);
                evenementSportifGestionnaire.addParticipantToEvenementSportif(selectedEvent.getId(), selectedAthlete.getId());
            }
        }
    }

    public void onRemoveClick() {
        String selectedDisciplineName = disciplineSelect.getSelectionModel().getSelectedItem();
        DisciplineSportive selectedDiscipline = disciplineSportiveGestionnaire.getDisciplineSportiveByName(selectedDisciplineName);
        String selectedEventName = eventSelect.getSelectionModel().getSelectedItem();
        EvenementSportif selectedEvent = evenementSportifGestionnaire.getEvenementSportifByName(selectedEventName);

        for (CheckBox checkBox : participantChecks) {
            if (checkBox.isSelected()) {
                String athleteName = checkBox.getText();
                Athlete selectedAthlete = athleteGestionnaire.getAthleteByName(athleteName);
                evenementSportifGestionnaire.removeParticipantFromEvenementSportif(selectedEvent.getId(), selectedAthlete.getId());
            }
        }
    }

}

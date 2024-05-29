package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import org.example.projet_java_2024.backend.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.projet_java_2024.frontend.AccueilController.*;
import static org.example.projet_java_2024.frontend.AccueilController.ATHLETE_GESTIONNAIRE;

public class EventAssignController extends EventController {
    @FXML
    private Button assigner;
    @FXML
    private Button remove;

    @FXML
    private VBox checkBoxContainer;

    @FXML
    private List<CheckBox> athleteChecks = new ArrayList<>();

    @FXML
    public void initialize() {
        List<Athlete> allAthletes = ATHLETE_GESTIONNAIRE.getAllAthletes();
        for (Athlete athlete : allAthletes) {
            CheckBox checkBox = new CheckBox(athlete.getNom());
            athleteChecks.add(checkBox);
            checkBoxContainer.getChildren().add(checkBox);

            // left margin for the eyes
            VBox.setMargin(checkBox, new Insets(0, 0, 0, 10));

            // check if athlete is already assigned to event
            if (SELECTED_EVENT != null) {
                List<Integer> athletesId = SELECTED_EVENT.getAthletesId();
                if (athletesId.contains(athlete.getId())) {
                    checkBox.setSelected(true);
                }
            }
        }
    }

    public void onAssignerClick(ActionEvent e) throws IOException {
        // remove all athletes from event
        List<Integer> athletesId = new ArrayList<>(SELECTED_EVENT.getAthletesId());
        for (Integer athleteId : athletesId) {
            EVENEMENT_GESTIONNAIRE.removeAthleteFromEvenementSportif(SELECTED_EVENT.getId(), athleteId);
        }

        // add selected athletes to event
        for (CheckBox checkBox : athleteChecks) {
            if (checkBox.isSelected()) {
                String athleteName = checkBox.getText();
                Athlete selectedAthlete = ATHLETE_GESTIONNAIRE.getAthleteByName(athleteName);
                EVENEMENT_GESTIONNAIRE.addAthleteToEvenementSportif(SELECTED_EVENT.getId(), selectedAthlete.getId());
            }
        }

        loadScene("/org/example/projet_java_2024/frontend/EventScene.fxml", "Discipline", e);
    }
}

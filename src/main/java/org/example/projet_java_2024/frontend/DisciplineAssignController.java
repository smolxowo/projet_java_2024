package org.example.projet_java_2024.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import org.example.projet_java_2024.backend.Athlete;
import org.example.projet_java_2024.backend.DisciplineSportive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineAssignController extends DisciplineController {
    @FXML
    private Button assigner;

    @FXML
    private VBox checkBoxContainer;
    @FXML
    private List<CheckBox> athleteChecks = new ArrayList<>(); // Liste pour stocker vos CheckBox

    @FXML
    public void initialize() {
        List<Athlete> allAthletes = ATHLETE_GESTIONNAIRE.getAllAthletes();
        // get athletes for the selected discipline
        List<Integer> athletesId = SELECTED_DISCIPLINE.getAthletesId();

        for (Athlete athlete : allAthletes) {
            CheckBox checkBox = new CheckBox(athlete.getNom());
            athleteChecks.add(checkBox);
            checkBoxContainer.getChildren().add(checkBox);

            // left margin for the eyes
            VBox.setMargin(checkBox, new Insets(0, 0, 0, 10));

            // check the box if athlete is in this discipline
            if (athletesId.contains(athlete.getId())) {
                checkBox.setSelected(true);
            }
        }
    }

    public void onAssignerClick(ActionEvent e) throws IOException {
        String selectedDisciplineName = SELECTED_DISCIPLINE.getNom();

        // remove all existing athletes from the discipline
        List<Integer> athletesId = new ArrayList<>(SELECTED_DISCIPLINE.getAthletesId());
        for (Integer athleteId : athletesId) {
            DISCIPLINE_GESTIONNAIRE.removeAthleteFromDisciplineSportive(SELECTED_DISCIPLINE.getId(), athleteId);
        }

        // add the selected athletes to the discipline
        for (CheckBox checkBox : athleteChecks) {
            if (checkBox.isSelected()) {
                String athleteName = checkBox.getText();
                Athlete selectedAthlete = ATHLETE_GESTIONNAIRE.getAthleteByName(athleteName);
                DISCIPLINE_GESTIONNAIRE.addAthleteToDisciplineSportive(SELECTED_DISCIPLINE.getId(), selectedAthlete.getId());
            }
        }

        loadScene("/org/example/projet_java_2024/frontend/DisciplineScene.fxml", "Discipline", e);
    }
}







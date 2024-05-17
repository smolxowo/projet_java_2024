package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class AccueilController {
    @FXML
    private SplitMenuButton athleteMenuButton, disciplineMenuButton, eventMenuButton, resultsMenuButton;

    @FXML
    private MenuItem addAthleteMenuItem, deleteAthleteMenuItem, updateAthleteMenuItem;

    @FXML
    private MenuItem addDisciplineMenuItem, deleteDisciplineMenuItem, assignDisciplineMenuItem;

    @FXML
    private MenuItem addEventMenuItem, deleteEventMenuItem, assignEventMenuItem;

    @FXML
    private MenuItem addResultMenuItem, deleteResultMenuItem, rankResultMenuItem;
}

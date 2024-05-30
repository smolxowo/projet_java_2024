package org.example.projet_java_2024.frontend;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Map;

public class ResultatClassementController extends ResultatController {
    @FXML
    private Tab tabPays, tabDiscipline;

    @FXML
    private TableView<LigneClassementPays> resultatsTableViewPays;
    @FXML
    private TableColumn<LigneClassementPays, String> paysColumn;
    @FXML
    private TableColumn<LigneClassementPays, Integer> orPaysColumn, argentPaysColumn, bronzePaysColumn;

    @FXML
    private TableView<LigneClassementDiscipline> resultatsTableViewDiscipline;
    @FXML
    private TableColumn<LigneClassementDiscipline, String> disciplineColumn;
    @FXML
    private TableColumn<LigneClassementDiscipline, Integer> orDisciplineColumn, argentDisciplineColumn, bronzeDisciplineColumn;

    @FXML
    public void initialize() {
        // Par pays
        paysColumn.setCellValueFactory(new PropertyValueFactory<>("pays"));
        orPaysColumn.setCellValueFactory(new PropertyValueFactory<>("or"));
        argentPaysColumn.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronzePaysColumn.setCellValueFactory(new PropertyValueFactory<>("bronze"));

        // Par discipline
        disciplineColumn.setCellValueFactory(new PropertyValueFactory<>("discipline"));
        orDisciplineColumn.setCellValueFactory(new PropertyValueFactory<>("or"));
        argentDisciplineColumn.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronzeDisciplineColumn.setCellValueFactory(new PropertyValueFactory<>("bronze"));

        loadResultatClassement();
    }

    public void loadResultatClassement() {
        if (resultatsTableViewPays != null) {
            // On vide le tableau
            resultatsTableViewPays.getItems().clear();

            Map<String, Map<String, Integer>> medalsByCountry = RESULTAT_GESTIONNAIRE.getMedalsByCountry();

            // Puis on le remplit : l nom du pays, le nombre de médailles d'or, d'argent et de bronze
            for (String country : medalsByCountry.keySet()) {
                int or = medalsByCountry.get(country).getOrDefault("Or", 0);
                int argent = medalsByCountry.get(country).getOrDefault("Argent", 0);
                int bronze = medalsByCountry.get(country).getOrDefault("Bronze", 0);

                // Créer une nouvelle ligne avec les données
                LigneClassementPays ligne = new LigneClassementPays(country, or, argent, bronze);

                // Ajouter la ligne à la TableView
                resultatsTableViewPays.getItems().add(ligne);
            }
        }

        if (resultatsTableViewDiscipline != null) {
            // On vide le tableau
            resultatsTableViewDiscipline.getItems().clear();

            Map<String, Map<String, Integer>> medalsByDiscipline = RESULTAT_GESTIONNAIRE.getMedalsByDiscipline();

            // Puis on le remplit : le nom de la discipline, le nombre de médailles d'or, d'argent et de bronze
            for (String discipline : medalsByDiscipline.keySet()) {
                int or = medalsByDiscipline.get(discipline).getOrDefault("Or", 0);
                int argent = medalsByDiscipline.get(discipline).getOrDefault("Argent", 0);
                int bronze = medalsByDiscipline.get(discipline).getOrDefault("Bronze", 0);

                // Créer une nouvelle ligne avec les données
                LigneClassementDiscipline ligne = new LigneClassementDiscipline(discipline, or, argent, bronze);

                // Ajouter la ligne à la TableView
                resultatsTableViewDiscipline.getItems().add(ligne);
            }
        }
    }
}
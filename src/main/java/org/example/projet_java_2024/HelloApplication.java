package org.example.projet_java_2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.projet_java_2024.backend.AthleteGestionnaire;
import org.example.projet_java_2024.backend.DisciplineSportiveGestionnaire;
import org.example.projet_java_2024.backend.EvenementSportifGestionnaire;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /* AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();
        athleteGestionnaire.addAthlete(
                "bob",
                "m",
                "france",
                4,
                8
        );

        DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();
        EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();

        // ajoute un evenement sportif
        evenementSportifGestionnaire.addEvenementSportif(
                "course",
                1
        ); */


        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("/org/example/projet_java_2024/frontend/AccueilScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
    }
}
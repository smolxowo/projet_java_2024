package org.example.projet_java_2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.projet_java_2024.backend.AthleteStorage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        AthleteStorage athleteStorage = new AthleteStorage();
        // Test addition of an athlete
        int billyId = athleteStorage.addAthlete("Billy Jackson", "M", "JP", 33, 2);
        athleteStorage.updateAthlete(billyId, "BOBBIE Jackson", "M", "JP", 33, 3);

        System.out.println(athleteStorage.getAllAthletes());
        launch();
    }
}
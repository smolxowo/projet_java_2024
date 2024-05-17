package org.example.projet_java_2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.projet_java_2024.backend.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AccueilScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        AthleteGestionnaire athleteGestionnaire = new AthleteGestionnaire();
        DisciplineSportiveGestionnaire disciplineSportiveGestionnaire = new DisciplineSportiveGestionnaire();
        EvenementSportifGestionnaire evenementSportifGestionnaire = new EvenementSportifGestionnaire();
        ParticipantGestionnaire participantGestionnaire = new ParticipantGestionnaire();
        ResultatGestionnaire resultatGestionnaire = new ResultatGestionnaire();

        // Test AthleteGestionnaire
        int newAthleteId = athleteGestionnaire.addAthlete(
                "Nom athlete",
                "Sexe athlete",
                "Pays athlete",
                33,
                1);
        athleteGestionnaire.updateAthlete(
                newAthleteId,
                "Updated Nom athlete",
                "Updated Sexe athlete",
                "Updated Pays athlete",
                34,
                2);

        // Test DisciplineSportiveGestionnaire
        List<Integer> athletesId = new ArrayList<>();
        athletesId.add(1);
        athletesId.add(2);
        int newDisciplineSportifId = disciplineSportiveGestionnaire.addDisciplineSportif("New Discipline", athletesId);
        athletesId.add(3);
        disciplineSportiveGestionnaire.updateDisciplineSportif(newDisciplineSportifId, "Updated Discipline", athletesId);

        // Test EvenementSportifGestionnaire
        List<Integer> participantsId = new ArrayList<>();
        participantsId.add(1);
        participantsId.add(2);
        int newEvenementSportifId = evenementSportifGestionnaire.addEvenementSportif("New Event", participantsId);
        participantsId.add(3);
        evenementSportifGestionnaire.updateEvenementSportif(newEvenementSportifId, "Updated Event", participantsId);

        // Test ParticipantGestionnaire
        int newParticipantId = participantGestionnaire.addParticipant(1, 1);
        participantGestionnaire.updateParticipant(newParticipantId, 2, 1);

        // Test ResultatGestionnaire
        int newResultatId = resultatGestionnaire.addResultat(1, 1, 222, "2024-05-12 22:22:22", "Medaille");
        resultatGestionnaire.updateResultat(newResultatId, newAthleteId, newEvenementSportifId, 333, "2024-05-12 22:22:22", "Updated Medaille");
        System.out.println(athleteGestionnaire.getAllAthletes());
        launch();
    }
}
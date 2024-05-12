package org.example.projet_java_2024.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultat {
    private Athlete athlete;
    private EvenementSportif evenement;
    private int score;
    private LocalDateTime temps;
    private String medaille;
}
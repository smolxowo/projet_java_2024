package org.example.projet_java_2024.backend;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultat {
    private int id;
    private int athleteId;
    private int evenementSportifId;
    private int score;
    private String date;
    private String medaille;
}
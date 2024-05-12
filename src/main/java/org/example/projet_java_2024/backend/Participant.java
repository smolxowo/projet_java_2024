package org.example.projet_java_2024.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private int id;
    private int athleteId;
    private int evenementSportifId;
}
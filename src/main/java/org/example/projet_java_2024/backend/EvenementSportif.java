package org.example.projet_java_2024.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvenementSportif {
    private int id;
    private String nom;
    private int disciplineSportifId;
    private List<Integer> athletesId;
}
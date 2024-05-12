package org.example.projet_java_2024.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EvenementSportif extends DisciplineSportive {
    private int id;
    private String nom;
    private List<Integer> participantsId;
}
package org.example.projet_java_2024.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineSportive {
    private String nomDS;
    private List<Athlete> participantDS;
}
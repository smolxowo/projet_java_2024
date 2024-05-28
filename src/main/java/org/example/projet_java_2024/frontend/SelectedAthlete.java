package org.example.projet_java_2024.frontend;

import lombok.Getter;
import lombok.Setter;
import org.example.projet_java_2024.backend.Athlete;

public class SelectedAthlete {
    @Setter
    @Getter
    private static Athlete selectedAthlete;
}
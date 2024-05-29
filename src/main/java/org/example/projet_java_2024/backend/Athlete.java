package org.example.projet_java_2024.backend;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {
    private int id;
    private String nom;
    private String sexe;
    private String pays;
    private int age;
    private int nbParticipation; // Le nombre de participations aux JO précédents
}
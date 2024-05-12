package org.example.projet_java_2024.backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Athlete {
    private String nomAthlete;
    private String sexe;
    private String pays;
    private int age;
    private int nbParticipation;        //le nombre de participations aux JO précédents
}
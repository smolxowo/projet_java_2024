package org.example.projet_java_2024.backend;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {
    private int id;
    private String nomAthlete;
    private String sexe;
    private String pays;
    private int age;
    private int nbParticipation;        //le nombre de participations aux JO précédents

    /*public Athlete(String nomAthlete,
                   String sexe,
                   String pays,
                   int age,
                   int nbParticipation) {
        this.nomAthlete = nomAthlete;
        this.sexe = sexe;
        this.pays = pays;
        this.age = age;
        this.nbParticipation = nbParticipation;
    }*/
}
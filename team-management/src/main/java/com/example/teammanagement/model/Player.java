package com.example.teammanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long playerId;
    @Column
    private String name;
    @Column
    private int age;

}

package com.example.teammanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long teamId;
    @Column
    private String name;
    @Column
    private String location;
}

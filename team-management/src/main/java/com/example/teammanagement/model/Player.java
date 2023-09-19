package com.example.teammanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "player_coach",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id")
    )
    private List<Coach> coachList;

}

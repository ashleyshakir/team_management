package com.example.teammanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long coachId;
    @Column
    private String firstName;
    @Column
    private String lastName;

}

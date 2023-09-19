package com.example.teammanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy = "coachList")
    private List<Player> playerList;

    public Coach() {
    }

    public Coach(Long coachId, String firstName, String lastName, Team team) {
        this.coachId = coachId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "coachId=" + coachId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", team=" + team +
                '}';
    }
}

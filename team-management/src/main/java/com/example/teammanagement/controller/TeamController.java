package com.example.teammanagement.controller;

import com.example.teammanagement.model.Team;
import com.example.teammanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/teams/{teamId}")
    public Optional<Team> getTeam(@PathVariable(value = "teamId") Long teamId){
        return teamService.getTeam(teamId);
    }

    @GetMapping(path = "/teams/")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }
}

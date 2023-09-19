package com.example.teammanagement.controller;

import com.example.teammanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}

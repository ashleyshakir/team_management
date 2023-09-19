package com.example.teammanagement.controller;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "/{playerId}/coaches/") // http://localhost:9094/api/players/1/coaches/
    public String getPlayerCoaches(@PathVariable(value = "playerId") Long playerId){
        return playerService.getPlayerCoaches(playerId);
    }

    @GetMapping(path="/{playerId}")
    public String getPlayerTeam(@PathVariable(value = "playerId") Long playerId){
        return playerService.getPlayerTeam(playerId);
    }
}

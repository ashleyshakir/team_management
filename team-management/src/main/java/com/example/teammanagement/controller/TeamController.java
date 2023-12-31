package com.example.teammanagement.controller;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Player;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/teams/")
    public Team createTeam(@RequestBody Team teamObject) {
        return teamService.createTeam(teamObject);
    }

    @GetMapping(path = "/teams/{teamId}")
    public Optional<Team> getTeam(@PathVariable(value = "teamId") Long teamId) {
        return teamService.getTeam(teamId);
    }

    @GetMapping(path = "/teams/")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @PutMapping(path = "/teams/{teamId}")
    public Team updateTeam(@PathVariable(value = "teamId") Long teamId, @RequestBody Team teamObject) {
        return teamService.updateTeam(teamId, teamObject);
    }

    @DeleteMapping(path = "/teams/{teamId}")
    public void deleteTeam(@PathVariable(value = "teamId") Long teamId) {
        teamService.deleteTeam(teamId);
    }

    @PostMapping(path = "/teams/{teamId}/coaches/")
    public Coach createTeamCoach(@PathVariable(value = "teamId") Long teamId, @RequestBody Coach coachObject) {
        return teamService.createTeamCoach(teamId, coachObject);
    }

    @GetMapping(path = "/teams/{teamId}/coaches/{coachId}")
    public Optional<Coach> getTeamCoach(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "coachId") Long coachId) {
        return teamService.getTeamCoach(teamId, coachId);
    }

    @GetMapping(path = "/teams/{teamId}/coaches/")
    public List<Coach> getTeamCoaches(@PathVariable(value = "teamId") Long teamId){
        return teamService.getTeamCoaches(teamId);
    }

    @PutMapping(path = "/teams/{teamId}/coaches/{coachId}")
    public Coach updateTeamCoach(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "coachId") Long coachId, @RequestBody Coach coachObject) {
        return teamService.updateTeamCoach(teamId,coachId,coachObject);
    }

    @DeleteMapping(path = "/teams/{teamId}/coaches/{coachId}")
    public void deleteTeamCoach(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "coachId") Long coachId){
        teamService.deleteTeamCoach(teamId,coachId);
    }

    @PostMapping(path = "/teams/{teamId}/players/")
    public Player createTeamPlayer(@PathVariable(value = "teamId") Long teamId, @RequestBody Player playerObject){
        return teamService.createTeamPlayer(teamId,playerObject);
    }

    @GetMapping(path = "/teams/{teamId}/players/{playerId}")
    public Optional<Player> getTeamPlayer(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "playerId") Long playerId) {
        return teamService.getTeamPlayer(teamId, playerId);
    }

    @GetMapping(path = "/teams/{teamId}/players/")
    public List<Player> getTeamPlayers(@PathVariable(value = "teamId") Long teamId){
        return teamService.getTeamPlayers(teamId);
    }

    @PutMapping(path = "/teams/{teamId}/players/{playerId}")
    public Player updateTeamPlayer(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "playerId") Long playerId, @RequestBody Player playerObject) {
        return teamService.updateTeamPlayer(teamId,playerId,playerObject);
    }

    @DeleteMapping(path = "/teams/{teamId}/players/{playerId}")
    public void deleteTeamPlayer(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "playerId") Long playerId){
        teamService.deleteTeamPlayer(teamId,playerId);
    }

}

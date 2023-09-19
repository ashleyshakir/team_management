package com.example.teammanagement.service;

import com.example.teammanagement.exception.InformationNotFoundException;
import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Player;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.PlayerRepository;
import com.example.teammanagement.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /**
     * Retrieve the list of coaches for a specific player.
     * @param playerId The unique ID for the player to retrieve the coach list for.
     * @return A String list of the player's coaches.
     */
    public String getPlayerCoaches(Long playerId){
        Optional<Player> player = playerRepository.findById(playerId);
        if(player.isEmpty()){
            throw new InformationNotFoundException("No player found with id " + playerId);
        }
        List<Coach> teamCoachList = player.get().getTeam().getCoachList();
        if(teamCoachList.isEmpty()){
            return "No coaches assigned.";
        }
        String coaches = "";
        for (Coach coach : teamCoachList) {
            String lastName = coach.getLastName();
            coaches += "Coach " + lastName + "\n";
        }
        return coaches;
    }

    /**
     * Retrieves name of the team a specific player is assigned to.
     * @param playerId The unique ID of the player.
     * @return A string representing the player's team name.
     */
    public String getPlayerTeam(Long playerId){
        Optional<Player> player = playerRepository.findById(playerId);
        if(player.isEmpty()){
            throw new InformationNotFoundException("No player found with id " + playerId);
        }
        return "Team: " + player.get().getTeam().getName();
    }

}

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
     * Retrieve list of coaches for specific player.
     * @param playerId The unique ID for the player to retrieve coach list for.
     * @return A list of the player's coaches.
     */
    public List<Coach> getPlayerCoaches(Long playerId){
        Optional<Player> player = playerRepository.findById(playerId);
        if(player.isEmpty()){
            throw new InformationNotFoundException("No player found with id " + playerId);
        }
        return player.get().getCoachList();
    }

}

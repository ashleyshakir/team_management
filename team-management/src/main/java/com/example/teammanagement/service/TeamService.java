package com.example.teammanagement.service;

import com.example.teammanagement.exception.InformationNotFoundException;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    /**
     * Retrieve a Team object from the database by its id.
     * @param teamId The unique id of the team to be retrieved.
     * @return The team object.
     */
    public Optional<Team> getTeam(Long teamId){
        Optional<Team> team = teamRepository.findById(teamId);
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found.");
        }
        return team;
    }

    /**
     * Retrieve all teams from the database.
     * @return A list of team objects.
     */
    public List<Team> getTeams(){
        List<Team> teamList = teamRepository.findAll();
        if(teamList.isEmpty()){
            throw new InformationNotFoundException("No teams found.");
        }
        return teamList;
    }

}

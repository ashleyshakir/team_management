package com.example.teammanagement.service;

import com.example.teammanagement.exception.InformationExistException;
import com.example.teammanagement.exception.InformationNotFoundException;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.TeamRepository;
import com.example.teammanagement.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TeamService {
    Logger logger = Logger.getLogger(TeamService.class.getName());

    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }
    /**
     * Retrieves the current logged-in user from the security context.
     * @return Returns the user object.
     */
    public static User getCurrentLoggedInUser(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Create a new team object.
     * @param teamObject The requested team object the user wants to create.
     * @return The newly created team object.
     */
    public Team createTeam(Team teamObject){
        logger.info("User ID: " + TeamService.getCurrentLoggedInUser().getUserId());
        Team team = teamRepository.findByNameAndUser_UserId(teamObject.getName(),TeamService.getCurrentLoggedInUser().getUserId());
        if(team != null){
            throw new InformationExistException("A team with the name " + teamObject.getName() + " already exists.");
        }
        teamObject.setUser(TeamService.getCurrentLoggedInUser());
        return teamRepository.save(teamObject);
    }


    /**
     * Retrieve a Team object from the database by its id.
     * @param teamId The unique id of the team to be retrieved.
     * @return The team object.
     */
    public Optional<Team> getTeam(Long teamId){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        return team;
    }

    /**
     * Retrieve all teams from the database.
     * @return A list of team objects.
     */
    public List<Team> getTeams(){
        List<Team> teamList = teamRepository.findByUser_UserId(TeamService.getCurrentLoggedInUser().getUserId());
        if(teamList.isEmpty()){
            throw new InformationNotFoundException("No teams found for user with id " + TeamService.getCurrentLoggedInUser().getUserId());
        }
        return teamList;
    }

    /**
     * Update an existing team object by finding the team's id and replacing
     * its data with the requested teamObject data.
     * @param teamId The unique id of the team to be updated.
     * @param teamObject The requested team object containing new data to update the existing team object with.
     * @return The updated team object.
     */
    public Team updateTeam(Long teamId, Team teamObject){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to user with id " + TeamService.getCurrentLoggedInUser().getUserId());
        } else {
            if(teamObject.getName().equals(team.get().getName())){
                throw new InformationExistException("A team with the name " + teamObject.getName() + " already exists.");
            } else{
                teamObject.setTeamId(teamId);
                teamObject.setUser(TeamService.getCurrentLoggedInUser());
                return teamRepository.save(teamObject);
            }
        }
    }

    /**
     * Deletes a team object from the repository by its unique ID.
     * @param teamId The unique ID of the team to be deleted.
     */
    public void deleteTeam(Long teamId){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to user with id "+ TeamService.getCurrentLoggedInUser().getUserId());
        }
        teamRepository.delete(team.get());
    }

}

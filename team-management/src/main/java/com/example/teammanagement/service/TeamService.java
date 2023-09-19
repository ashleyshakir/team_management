package com.example.teammanagement.service;

import com.example.teammanagement.exception.InformationExistException;
import com.example.teammanagement.exception.InformationNotFoundException;
import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Player;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.CoachRepository;
import com.example.teammanagement.repository.PlayerRepository;
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
    private CoachRepository coachRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }
    @Autowired
    public void setCoachRepository(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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

    /**
     * Create a new coach object that belongs to a team.
     * @param teamId The unique id of the team the coach belongs to.
     * @param coachObject The requested coach object the user wants to create.
     * @return The newly created coach.
     */
    public Coach createTeamCoach(Long teamId, Coach coachObject){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        Coach coach = coachRepository.findByFirstNameAndLastName(coachObject.getFirstName(),coachObject.getLastName());
        if(coach != null){
            throw new InformationExistException("Coach " +coachObject.getFirstName() +" " + coachObject.getLastName() + " already exists.");
        }
        coachObject.setUser(TeamService.getCurrentLoggedInUser());
        coachObject.setTeam(team.get());
        return coachRepository.save(coachObject);
    }

    /**
     * Retrieve a coach from a specific team by ID.
     * @param teamId The unique ID of the team you want to retrieve the coach from.
     * @param coachId The unique ID of the coach you want to retrieve.
     * @return The coach object.
     */
    public Optional<Coach> getTeamCoach(Long teamId, Long coachId){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        Optional<Coach> coach = coachRepository.findByTeam_TeamId(teamId).stream().filter(c -> c.getCoachId().equals(coachId)).findFirst();
        if(coach.isEmpty()){
            throw new InformationNotFoundException("Coach with id "+ coachId + " does not belong to this team or does not exist");
        }
        return coach;
    }

    /**
     * Get a list of all coaches belonging to specific team.
     * @param teamId The unique ID of the team to retrieve all coaches from.
     * @return A list of coaches.
     */
    public List<Coach> getTeamCoaches(Long teamId){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        return team.get().getCoachList();
    }

    /**
     * Update coach object of a specific team.
     * @param teamId The unique ID for the team from which the coach to update belongs.
     * @param coachId The unique ID of the coach to update.
     * @param coachObject The requested coach object containing new data to update the existing coach object with.
     * @return The updated coach object.
     */
    public Coach updateTeamCoach(Long teamId, Long coachId, Coach coachObject){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        Optional<Coach> coach = coachRepository.findByTeam_TeamId(teamId).stream().filter(c -> c.getCoachId().equals(coachId)).findFirst();
        if(coach.isEmpty()){
            throw new InformationNotFoundException("Coach with id "+ coachId + " does not belong to this team or does not exist");
        } else if (coach.get().getFirstName().equals(coachObject.getFirstName()) && coach.get().getLastName().equals(coachObject.getLastName())){
            throw new InformationExistException("Coach " +coachObject.getFirstName() +" " + coachObject.getLastName() + " already exists.");
        } else {
            coachObject.setCoachId(coachId);
            coachObject.setTeam(team.get());
            coachObject.setUser(TeamService.getCurrentLoggedInUser());
            return coachRepository.save(coachObject);
        }
    }

    /**
     * Delete a coach from a specific team.
     * @param teamId The unique ID of the team from which to delete a coach.
     * @param coachId The unique ID of the coach to delete.
     */
    public void deleteTeamCoach(Long teamId, Long coachId){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        Optional<Coach> coach = coachRepository.findByTeam_TeamId(teamId).stream().filter(c -> c.getCoachId().equals(coachId)).findFirst();
        if(coach.isEmpty()){
            throw new InformationNotFoundException("Coach with id "+ coachId + " does not belong to this team or does not exist");
        }
        coachRepository.delete(coach.get());
    }

    /**
     * Creates a new player associated with a team and the currently logged-in user.
     * @param teamId The unique ID of the team to which the player will belong.
     * @param playerObject The player object to be created.
     * @return The newly created player.
     */
    public Player createTeamPlayer(Long teamId, Player playerObject){
        Optional<Team> team = Optional.ofNullable(teamRepository.findByTeamIdAndUser_UserId(teamId, TeamService.getCurrentLoggedInUser().getUserId()));
        if(team.isEmpty()){
            throw new InformationNotFoundException("Team with id " + teamId + " not found or does not belong to this user.");
        }
        Player player = playerRepository.findByName(playerObject.getName());
        if(player != null){
            throw new InformationExistException("Player " +playerObject.getName() + " already exists.");
        }
        playerObject.setTeam(team.get());
        playerObject.setCoachList(team.get().getCoachList());
        playerObject.setUser(TeamService.getCurrentLoggedInUser());
        return playerRepository.save(playerObject);
    }


}

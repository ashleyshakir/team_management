package com.example.teammanagement.repository;

import com.example.teammanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository interface for managing team entities in the database.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    /**
     * Retrieve a list of teams belonging to a user with a specific ID.
     * @param userId The user ID of the user to retrieve the list of teams from.
     * @return A list of team objects belonging to the specific user.
     */
    List<Team> findByUser_UserId(Long userId);

    /**
     * Retrieves a team by its ID and user ID from the repository.
     * @param teamId The team ID.
     * @param userId The user ID associated with the team.
     * @return The team object with the specified ID and user ID.
     */
    Team findByTeamIdAndUser_UserId(Long teamId, Long userId);

    /**
     * Retrieves a team by its name and user ID from the repository.
     * @param teamName The name of the team to be retrieved.
     * @param userId The user ID associated with the team.
     * @return The Team object with the specified name and user ID.
     */
    Team findByNameAndUser_UserId(String teamName, Long userId);


}

package com.example.teammanagement.repository;

import com.example.teammanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing team entities in the database.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    /**
     * Finds a team by its name in the database.
     * @param teamName The name of the team to find.
     * @return The team associated with the name.
     */
    Team findByName(String teamName);

    /**
     * Retrieves a team by its ID and user ID from the repository.
     * @param teamId The team ID.
     * @param userId The user ID associated with the team.
     * @return The team object with the speciifed ID and user ID.
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

package com.example.teammanagement.repository;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository interface for managing coach entities in the database.
 */
@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    /**
     * Retrieve a coach by their first name and last name.
     * @param firstName The coaches first name.
     * @param lastName The coaches last name.
     * @return The coach object with the specified first and last name.
     */
    Coach findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieve a list of coaches belonging to a team with a specific ID.
     * @param teamId The ID of the team to retrieve the list of coaches from.
     * @return A list of coaches belonging to the specific team.
     */
    List<Coach> findByTeam_TeamId(Long teamId);
}

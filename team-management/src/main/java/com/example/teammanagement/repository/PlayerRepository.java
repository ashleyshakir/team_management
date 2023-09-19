package com.example.teammanagement.repository;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    /**
     * Find a player by their name.
     * @param name The player's name.
     * @return Return the player object with the specified name.
     */
    Player findByName(String name);
    /**
     * Retrieve a list of players belonging to a team with a specific ID.
     * @param teamId The ID of the team to retrieve the list of players from.
     * @return A list of players belonging to the specific team.
     */
    List<Player> findByTeam_TeamId(Long teamId);

}

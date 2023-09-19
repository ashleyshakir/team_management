package com.example.teammanagement.repository;

import com.example.teammanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing user entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Checks if a user with the specified email address exists in the database.
     * @param userEmailAddress The email address to check.
     * @return true if a user with the given email address exists; otherwise, false.
     */
    boolean existsByEmailAddress(String userEmailAddress);

    /**
     * Finds a user by their email address in the database.
     * @param emailAddress The email address of the user to find.
     * @return The user object associated with the email address, or null if not found.
     */
    User findUserByEmailAddress(String emailAddress);
}

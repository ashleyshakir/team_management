package com.example.teammanagement.repository;

import com.example.teammanagement.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    public Coach findByFirstNameAndLastNameAndUser_UserId(String firstName, String lastName, Long userId);
}

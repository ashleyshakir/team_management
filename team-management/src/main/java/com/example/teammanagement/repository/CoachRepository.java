package com.example.teammanagement.repository;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    public Coach findByFirstNameAndLastName(String firstName, String lastName);
    List<Coach> findByTeam_TeamId(Long coachId);
}

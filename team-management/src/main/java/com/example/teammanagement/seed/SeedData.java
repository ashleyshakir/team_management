package com.example.teammanagement.seed;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.CoachRepository;
import com.example.teammanagement.repository.TeamRepository;
import com.example.teammanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;

    public SeedData(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository, TeamRepository teamRepository, CoachRepository coachRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmailAddress("ashley@gmail.com");
        user.setPassword(passwordEncoder.encode("ash12345!"));
        userRepository.save(user);

        Team team = new Team();
        team.setName("Charlotte United");
        team.setLocation("Charlotte, NC");
        team.setUser(user);
        teamRepository.save(team);

        Coach coach1 = new Coach();
        coach1.setFirstName("T");
        coach1.setLastName("Shakir");
        coach1.setTeam(team);
        coach1.setUser(user);
        coachRepository.save(coach1);

    }
}

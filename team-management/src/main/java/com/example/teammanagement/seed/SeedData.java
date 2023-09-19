package com.example.teammanagement.seed;

import com.example.teammanagement.model.Coach;
import com.example.teammanagement.model.Player;
import com.example.teammanagement.model.Team;
import com.example.teammanagement.model.User;
import com.example.teammanagement.repository.CoachRepository;
import com.example.teammanagement.repository.PlayerRepository;
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
    private final PlayerRepository playerRepository;

    public SeedData(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository, TeamRepository teamRepository, CoachRepository coachRepository, PlayerRepository playerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.coachRepository = coachRepository;
        this.playerRepository = playerRepository;
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

        Player player1 = new Player();
        player1.setName("Billy");
        player1.setAge(10);
        player1.setTeam(team);
        player1.setUser(user);
        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setName("Thomas");
        player2.setAge(10);
        player2.setTeam(team);
        player2.setUser(user);
        playerRepository.save(player2);

        Player player3 = new Player();
        player3.setName("Ricky");
        player3.setAge(9);
        player3.setTeam(team);
        player3.setUser(user);
        playerRepository.save(player3);

    }
}

package ifs.edu.br.chatonlinebackend.config;

import ifs.edu.br.chatonlinebackend.model.User;
import ifs.edu.br.chatonlinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> lsUsers = List.of(
                new User(1, "Apolos7", passwordEncoder.encode("123")),
                new User(2, "batman", passwordEncoder.encode("1234")),
                new User(3, "superman", passwordEncoder.encode("1235")),
                new User(4, "spider-man", passwordEncoder.encode("1236")),
                new User(5, "sad-man", passwordEncoder.encode("1237"))
        );
        userRepository.saveAll(lsUsers);
    }
}

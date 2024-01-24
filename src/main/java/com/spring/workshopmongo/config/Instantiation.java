package com.spring.workshopmongo.config;

import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.dto.AuthorDTO;
import com.spring.workshopmongo.repository.PostRepository;
import com.spring.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withZone(ZoneId.of("GMT"));


        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null
                , LocalDate.parse("21/03/2018", timeFormatter)
                , "Partiu viagem"
                , "Vou viajar para São Paulo!"
                , new AuthorDTO(maria));
        Post post2 = new Post(null
                , LocalDate.parse("23/03/2018", timeFormatter)
                , "Bom dia"
                , "Acordei Feliz"
                , new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post1, post2));

    }
}

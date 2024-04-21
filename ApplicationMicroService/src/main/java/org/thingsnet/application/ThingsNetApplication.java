package org.thingsnet.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thingsnet.application.Config.RsaKeyConfigProperties;
import org.thingsnet.application.Entities.UserEntity;
import org.thingsnet.application.Repositories.UserRepository;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class ThingsNetApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThingsNetApplication.class, args);
    }

/*    @Bean
    public CommandLineRunner initializeUser(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {

            UserEntity user = new UserEntity();
            user.setEmail("example@gmail.com");
            user.setPassword(passwordEncoder.encode("examplepassword"));

            // Save the user to the database
            userRepository.save(user);

        };
    }*/

}

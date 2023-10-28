package backendspring.cardgame.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.Scanner;

@Configuration
public class AppConfiguration {

    @Bean
    public Random getRandom() {
        return new Random();
    }

//    @Bean
//    public Scanner getScanner() {
//        return new Scanner(System.in);
//    }
}

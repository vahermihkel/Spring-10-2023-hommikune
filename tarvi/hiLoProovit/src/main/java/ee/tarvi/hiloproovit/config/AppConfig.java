package ee.tarvi.hiloproovit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

// Automaatikaga kõigile teen mingeid konfiguratsioone
@Configuration
public class AppConfig {

    @Bean
    public Random getRandom(){
        return new Random();
    }
}

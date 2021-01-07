package annotations.config;

import annotations.domain.Coach;
import annotations.domain.FortuneService;
import annotations.domain.SadFortuneService;
import annotations.domain.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("annotations.domain")
@PropertySource("classpath:properties/sport.properties")
public class SportConfig {

    // define bean for our sad fortune service
    @Bean
    public FortuneService sadFortuneService() {
        return new SadFortuneService();
    }

    // define bean for our swim coach AND inject dependency
    @Bean
    public Coach swimCoach() {
        SwimCoach mySwimCoach = new SwimCoach(sadFortuneService());
        return mySwimCoach;
    }

}

package queue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }
}

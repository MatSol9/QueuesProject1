package queue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.Mockito.mock;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {

    @Bean
    WebClient webClient() {
        return mock(WebClient.class);
    }
}

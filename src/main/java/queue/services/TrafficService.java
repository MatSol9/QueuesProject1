package queue.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TrafficService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficService.class);
    private final WebClient webClient;

    public TrafficService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendRequest() {
        LOGGER.info("Sending request");
    }
}

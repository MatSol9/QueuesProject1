package queue.services;

import model.BasicEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import requests.BasicDataRequest;

import java.util.Objects;

@Service
public class TrafficService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficService.class);
    private final WebClient webClient;
    @Value("${connections.proxy}")
    private String proxyUrl;

    public TrafficService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendRequest() {
        LOGGER.info("Sending request to {}", proxyUrl);
        BasicEntry basicEntry = webClient.method(HttpMethod.GET)
                .uri(proxyUrl + "/data/")
                .bodyValue(new BasicDataRequest("Loc"))
                .retrieve()
                .bodyToMono(BasicEntry.class)
                .block();
        LOGGER.info("Received {}", basicEntry);
    }
}

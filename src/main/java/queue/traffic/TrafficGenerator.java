package queue.traffic;

import model.BasicEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import queue.services.TrafficService;
import requests.BasicDataRequest;

@Service
public class TrafficGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficGenerator.class);
    private final TrafficService trafficService;
    private final WebClient webClient;
    @Value("${connections.proxy}")
    private String proxyUrl;

    public TrafficGenerator(TrafficService trafficService, WebClient webClient) {
        this.trafficService = trafficService;
        this.webClient = webClient;
    }

    @Scheduled(fixedRate = 1000)
    void sendRequest() {
        LOGGER.info("Creating request");
        LOGGER.info("Sending request to {}", proxyUrl);
        webClient.method(HttpMethod.GET)
                .uri(proxyUrl + "/data/")
                .bodyValue(new BasicDataRequest("Loc"))
                .retrieve()
                        .bodyToMono(BasicEntry.class)
                .elapsed()
                                .subscribe(basicEntry -> LOGGER.info("Received {}", basicEntry.getT1()));
    }
}

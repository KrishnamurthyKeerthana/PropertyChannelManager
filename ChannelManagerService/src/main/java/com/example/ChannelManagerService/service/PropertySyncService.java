package com.example.ChannelManagerService.service;

import com.example.ChannelManagerService.model.Booking;
import com.example.ChannelManagerService.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class PropertySyncService {

    private final WebClient webClientAirbnb;
    private final WebClient webClientAgoda;

    @Value("${airbnb.api.url}")
    private String airbnbApiUrl;

    @Value("${agoda.api.url}")
    private String agodaApiUrl;

    @Autowired
    public PropertySyncService(WebClient.Builder webClientBuilder,
                               @Value("${airbnb.api.url}") String airbnbApiUrl,
                               @Value("${agoda.api.url}") String agodaApiUrl) {
        this.webClientAirbnb = webClientBuilder.baseUrl(airbnbApiUrl).build();
        this.webClientAgoda = webClientBuilder.baseUrl(agodaApiUrl).build();
    }

    public Mono<String> syncToAirbnb(Property property) {
        return webClientAirbnb.post()
                .uri("/api/airbnb/properties")
                .bodyValue(property)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> syncToAgoda(Property property) {
        return webClientAgoda.post()
                .uri("/api/agoda/properties")
                .bodyValue(property)
                .retrieve()
                .bodyToMono(String.class);
    }

    public void updateAvailabilityAcrossOTAs(String propertyId, String checkInDate, String checkOutDate) {
        // Notify Airbnb
        webClientAirbnb.post()
                .uri("/api/airbnb/properties/" + propertyId + "/block-dates")
                .bodyValue(Map.of("checkInDate", checkInDate, "checkOutDate", checkOutDate))
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Availability updated on Airbnb: " + response))
                .subscribe();

        // Notify Agoda
        webClientAgoda.post()
                .uri("/api/agoda/properties/" + propertyId + "/block-dates")
                .bodyValue(Map.of("checkInDate", checkInDate, "checkOutDate", checkOutDate))
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Availability updated on Agoda: " + response))
                .subscribe();
    }

    public void notifyPMS(Booking booking) {
        // Simulate sending booking data to the PMS
        System.out.println("Notifying PMS of booking: " + booking);
        // In a real application, this might involve an API call to the PMS
        // For example, using WebClient to post booking data to a PMS endpoint
    }

}

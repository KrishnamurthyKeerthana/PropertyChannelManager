package com.example.AirbnbService.controller;

import com.example.AirbnbService.model.Booking;
import com.example.AirbnbService.model.Property;
import com.example.AirbnbService.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/airbnb")
public class PropertyController {

        @Autowired
        private PropertyRepository propertyRepository;

        @PostMapping("/properties")
        public ResponseEntity<String> addProperty(@RequestBody Property property) {
            propertyRepository.save(property); // Save property to the database
            return ResponseEntity.ok("Property added successfully to Airbnb");
        }

        @GetMapping("/properties")
        public List<Property> getProperties() {
            return propertyRepository.findAll(); // Retrieve all properties from the database
        }

    @PostMapping("/properties/{propertyId}/book")
    public ResponseEntity<String> simulateBooking(@PathVariable String propertyId) {
        // Simulate booking details
        Booking booking = new Booking(propertyId, "John Doe", "2024-11-10", "2024-11-15", 2);

        // Notify Channel Manager of the booking
        WebClient.create("http://localhost:8082/api/channel-manager/bookings")
                .post()
                .bodyValue(booking)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Booking notification sent to Channel Manager: " + response))
                .subscribe();

        return ResponseEntity.ok("Booking simulated for property " + propertyId);
    }


    @PostMapping("/properties/{propertyId}/block-dates")
    public ResponseEntity<String> blockDates(@PathVariable String propertyId, @RequestBody Map<String, String> dates) {
        String checkInDate = dates.get("checkInDate");
        String checkOutDate = dates.get("checkOutDate");

        // Logic to block dates for the specified property
        System.out.println("Blocking dates for property " + propertyId + " from " + checkInDate + " to " + checkOutDate);

        return ResponseEntity.ok("Dates blocked successfully for property " + propertyId);
    }


}


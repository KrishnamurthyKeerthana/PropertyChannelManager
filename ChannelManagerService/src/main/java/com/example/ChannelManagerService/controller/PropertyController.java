package com.example.ChannelManagerService.controller;

import com.example.ChannelManagerService.model.Booking;
import com.example.ChannelManagerService.model.Property;
import com.example.ChannelManagerService.service.PropertySyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/channel-manager")
public class PropertyController {

    @Autowired
    private PropertySyncService propertySyncService;

    @PostMapping("/properties")
    public ResponseEntity<String> addProperty(@RequestBody Property property) {
        // Sync property data to Airbnb
        propertySyncService.syncToAirbnb(property)
                .doOnSuccess(response -> System.out.println("Property synced to Airbnb: " + response))
                .subscribe();

        // Sync property data to Agoda
        propertySyncService.syncToAgoda(property)
                .doOnSuccess(response -> System.out.println("Property synced to Agoda: " + response))
                .subscribe();

        return ResponseEntity.ok("Property added successfully to Channel Manager and syncing initiated");
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> receiveBooking(@RequestBody Booking booking) {
        System.out.println("Received booking notification for property: " + booking.getPropertyId());

        // Step 1: Update availability across OTAs
        propertySyncService.updateAvailabilityAcrossOTAs(booking.getPropertyId(), booking.getCheckInDate(), booking.getCheckOutDate());

        // Step 2: Forward booking information to PMS
        propertySyncService.notifyPMS(booking);

        return ResponseEntity.ok("Booking processed and availability updated across OTAs");
    }
}

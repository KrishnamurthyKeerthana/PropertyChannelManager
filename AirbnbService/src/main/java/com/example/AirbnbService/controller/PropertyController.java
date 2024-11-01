package com.example.AirbnbService.controller;

import com.example.AirbnbService.model.Property;
import com.example.AirbnbService.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}


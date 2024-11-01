package com.example.AgodaService.controller;

import com.example.AgodaService.model.Property;
import com.example.AgodaService.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agoda")
public class PropertyController {

        @Autowired
        private PropertyRepository propertyRepository;

        @PostMapping("/properties")
        public ResponseEntity<String> addProperty(@RequestBody Property property) {
            propertyRepository.save(property); // Save property to the database
            return ResponseEntity.ok("Property added successfully to Agoda");
        }

        @GetMapping("/properties")
        public List<Property> getProperties() {
            return propertyRepository.findAll(); // Retrieve all properties from the database
        }

}


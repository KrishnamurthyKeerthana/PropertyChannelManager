package com.example.AirbnbService.repository;

import com.example.AirbnbService.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, String> {
}

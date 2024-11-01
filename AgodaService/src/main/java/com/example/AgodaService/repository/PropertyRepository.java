package com.example.AgodaService.repository;

import com.example.AgodaService.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, String> {
}

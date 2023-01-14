package com.ejercicio.ejercObResJpa.repository;

import com.ejercicio.ejercObResJpa.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}

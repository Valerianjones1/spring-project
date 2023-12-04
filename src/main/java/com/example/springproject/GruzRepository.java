package com.example.springproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GruzRepository extends JpaRepository<Gruz, Long> {
    @Query("SELECT p FROM Gruz p WHERE CONCAT(p.gruzName,'', p.gruzInsides, '', p.departureDate, '', p.cityArrival, '', p.arrivalDate) LIKE %?1%")
    List<Gruz> search(String keyword);
}
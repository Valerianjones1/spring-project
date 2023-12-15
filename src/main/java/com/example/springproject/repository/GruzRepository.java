package com.example.springproject.repository;

import com.example.springproject.entity.Gruz;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GruzRepository extends JpaRepository<Gruz, Long> {
    @Query("SELECT p FROM Gruz p WHERE CONCAT(p.gruzName,'', p.gruzInsides, '', p.cityDeparture, '',p.departureDate, '', p.cityArrival, '', p.arrivalDate) LIKE %?1%")
    List<Gruz> search(String keyword);

    List<Gruz> findAll(Sort sort);


}
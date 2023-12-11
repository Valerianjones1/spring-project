package com.example.springproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Gruz {
    private Long id;
    private String gruzName;
    private String gruzInsides;
    private LocalDate departureDate;
    private String cityArrival;
    private LocalDate arrivalDate;
    private String cityDeparture;


    protected Gruz() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGruzName() {
        return gruzName;
    }

    public void setGruzName(String gruzName) {
        this.gruzName = gruzName;
    }

    public String getGruzInsides() {
        return this.gruzInsides;
    }

    public void setGruzInsides(String gruzInsides) {
        this.gruzInsides = gruzInsides;
    }

    public LocalDate getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getCityArrival() {
        return this.cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival;
    }

    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    public String getCityDeparture() {
        return cityDeparture;
    }

    public void setCityDeparture(String cityDeparture) {
        this.cityDeparture = cityDeparture;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        return "Gruz{" +
                "id=" + id +
                ", gruzName='" + gruzName + '\'' +
                ", gruzInsides='" + gruzInsides + '\'' +
                ", departureDate=" + departureDate +
                ", cityArrival='" + cityArrival + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", cityDeparture='" + cityDeparture + '\'' +
                '}';
    }
}
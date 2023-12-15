package com.example.springproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "gruz")
public class Gruz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Имя груза не может быть null.")
    @NotBlank(message = "Имя груза не может быть пустым.")
    private String gruzName;

    @NotNull(message = "Содержимое груза не может быть null.")
    @NotBlank(message = "Содержимое груза не может быть пустым.")
    private String gruzInsides;

    @NotNull(message = "Дата отправки не может быть null.")
    private LocalDate departureDate;

    @NotNull(message = "Город прибытия не может быть null.")
    @NotBlank(message = "Город прибытия не может быть пустым.")
    private String cityArrival;

    @NotNull(message = "Дата прибытия не может быть null.")
    private LocalDate arrivalDate;

    @NotNull(message = "Город отправки не может быть null.")
    @NotBlank(message = "Город отправки не может быть пустым.")
    private String cityDeparture;
}
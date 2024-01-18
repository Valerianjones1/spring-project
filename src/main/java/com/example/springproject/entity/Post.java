package com.example.springproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Загловок не может быть null.")
    @NotBlank(message = "Заголовок не может быть пустым.")
    private String header;

    @NotNull(message = "Содержимое тела не может быть null.")
    @NotBlank(message = "Содержимое тела не может быть пустым.")
    private String body;
}

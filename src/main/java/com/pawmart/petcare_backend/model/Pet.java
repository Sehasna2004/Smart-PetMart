package com.pawmart.petcare_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Cat, Dog, Bird
    private String breed;
    private int age;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl; // We will store the file path here
}
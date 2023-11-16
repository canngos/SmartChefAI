package com.project.smartchefservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

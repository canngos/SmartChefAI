package com.project.smartchefservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class RecipeHistory {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String recipeName;
    private String dishImage;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_steps", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "step", nullable = false)
    private List<String> steps;
}

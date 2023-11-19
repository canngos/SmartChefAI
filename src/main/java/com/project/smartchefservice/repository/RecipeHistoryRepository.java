package com.project.smartchefservice.repository;

import com.project.smartchefservice.entity.RecipeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeHistoryRepository extends JpaRepository<RecipeHistory, Long> {

    Optional<RecipeHistory> findByRecipeName(String recipeName);

    List<RecipeHistory> findAllByOrderByCreatedAtDesc();
}

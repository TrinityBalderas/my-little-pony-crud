package com.mlp.trinity.demo.repository;

import com.mlp.trinity.demo.models.PonyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PonyRepository extends JpaRepository<PonyModel, Long> {
    boolean existsByName(String name);
}

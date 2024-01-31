package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 Repositório da classe Fertilizer.
 */

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}

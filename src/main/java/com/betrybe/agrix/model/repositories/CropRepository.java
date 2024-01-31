package com.betrybe.agrix.model.repositories;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 Repositório da classe Crop.
 */

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findByHarvestDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}

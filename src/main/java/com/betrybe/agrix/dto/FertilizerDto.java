package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Fertilizer;

/**
 Dto da classe Fertilizer.
 */
public record FertilizerDto(
    Long id,
    String name,
    String brand,
    String composition
) {

  /**
   Método que tranforma Fertilizer em DTO.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}

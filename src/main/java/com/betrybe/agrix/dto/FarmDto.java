package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Farm;

/**
 DTO da classe Farm.
 */

public record FarmDto(
    Long id,
    String name,
    double size
) {
  /**
   Médoto que transforma a classe em dto.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(), farm.getName(), farm.getSize()
    );
  }
}

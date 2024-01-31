package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Crop;
import java.time.LocalDate;

/**
 Dto da classe Crop.
 */

public record CropDto(
    Long id,
    String name,
    LocalDate harvestDate,
    Double plantedArea,
    Long farmId,
    LocalDate plantedDate


) {
  /**
   Metodo que transforma o crop em dto.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getHarvestDate(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate()
    );
  }

}

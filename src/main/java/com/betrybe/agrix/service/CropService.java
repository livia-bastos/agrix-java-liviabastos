package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 Classe serviço.
 */

@Service

public class CropService {

  private CropRepository cropRepository;
  private FarmService farmService;

  /**
   contrutor com injeção de dependencia.
   */

  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService) {

    this.cropRepository = cropRepository;
    this.farmService = farmService;
  }

  /**
   Método que cria um crop a partir do id de uma farm e retorna o dto de um crop.
   */

  public CropDto createCrop(Crop crop, long id) throws FarmNotFoundException {
    Farm farm = farmService.findById(id);
    crop.setFarm(farm);
    Crop savedCrop = cropRepository.save(crop);
    return CropDto.fromEntity(savedCrop);
  }

  public List<Crop> findAllCrops() {
    return cropRepository.findAll();
  }

  /**
   Encontra a plantação pelo id.
   */

  public Crop findById(long id) throws CropNotFoundException {
    Optional<Crop> dbCrop = cropRepository.findById(id);

    if (dbCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return dbCrop.get();

  }

  public List<Crop> findLastCropsPlanted(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }
}


package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 Controlador da classe Farm.
 */

@RestController
@RequestMapping(value = "/farms")

public class FarmController {
  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   insere uma fazenda no bd.
   */

  @PostMapping()
  public ResponseEntity<FarmDto> createFarm(@RequestBody Farm farm) {
    Farm savedFarm = farmService.insertFarm(farm);
    FarmDto farmDto = FarmDto.fromEntity(savedFarm);
    return ResponseEntity.status(201).body(farmDto);
  }

  /**
   encontra todas as fazendas.
   */

  @GetMapping()
  public ResponseEntity<List<FarmDto>> findAll() {
    List<Farm> allFarms = farmService.findAll();
    List<FarmDto> farmList = allFarms.stream().map(FarmDto::fromEntity).toList();
    return ResponseEntity.status(200).body(farmList);
  }

  /**
   encontra fazenda pelo id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> findById(@PathVariable("id") long id) {
    Farm farm = farmService.findById(id);
    FarmDto farmDto = FarmDto.fromEntity(farm);
    return ResponseEntity.status(200).body(farmDto);
  }

  /**
   Cria um crop e relaciona com o id da fazenda.
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable("id") long id, @RequestBody Crop crop)
      throws FarmNotFoundException {

    CropDto savedCrop = cropService.createCrop(crop, id);
    return ResponseEntity.status(201).body(savedCrop);
  }

  /**
   Busca um crop e relaciona com o id da fazenda.
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<List<CropDto>> findAllCropsbyFarm(@PathVariable("id") long id)
      throws FarmNotFoundException {

    Farm farm = farmService.findById(id);

    List<Crop> allCrops = cropService.findAllCrops();
    List<CropDto> cropList = allCrops.stream()
        .map(CropDto::fromEntity).filter(cropDto -> id == cropDto.farmId()).toList();
    return ResponseEntity.status(200).body(cropList);
  }
}

package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 Controlador da Classe Fertilizer.
 */

@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;
  // private final CropService cropService;

  /**
   Construtor da classe.
   */

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   insere uma fertilizer no bd.
   */

  @PostMapping
  public ResponseEntity<FertilizerDto> insertFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer savedFertilizer = fertilizerService.insertFertilizer(fertilizer);
    FertilizerDto fertilizerDto = FertilizerDto.fromEntity(savedFertilizer);
    return ResponseEntity.status(201).body(fertilizerDto);
  }

  /**
   encontra todos os fertilizantes.
   */

  @GetMapping()
  @Secured("ROLE_ADMIN")
  public ResponseEntity<List<FertilizerDto>> findAll() {
    List<Fertilizer> fertilizers = fertilizerService.findAll();
    List<FertilizerDto> fertilizerDtoList = fertilizers.stream()
        .map(FertilizerDto::fromEntity).toList();
    return ResponseEntity.status(200).body(fertilizerDtoList);
  }

  /**
   Controlador da classe Fertilizer.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> findById(@PathVariable("id") long id) {
    Fertilizer fertilizer = fertilizerService.findById(id);
    FertilizerDto fertilizerDto = FertilizerDto.fromEntity(fertilizer);
    return ResponseEntity.status(200).body(fertilizerDto);
  }
}

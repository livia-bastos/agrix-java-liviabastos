package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 Controlador da classe Crop.
 */

@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;
  private final FertilizerService fertilizerService;

  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }
  /**
   Encontra todas as plantações.
   */

  @GetMapping
  public List<CropDto> findAll() {
    List<Crop> crops = cropService.findAllCrops();

    return crops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  /**
   Encontra a plantação pelo id.
   */

  @GetMapping("/{id}")
  public ResponseEntity<CropDto> findById(@PathVariable("id") long id) {
    Crop crop = cropService.findById(id);
    CropDto cropDto = CropDto.fromEntity(crop);
    return ResponseEntity.status(200).body(cropDto);
  }

  /**
   Rota que busca pelas datas que vem do parametro.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsBetweenDates(@RequestParam("start") LocalDate start,
                                                            @RequestParam("end") LocalDate end) {
    List<Crop> searchCrop = cropService.findLastCropsPlanted(start, end);
    List<CropDto> cropDto = searchCrop.stream()
        .map(CropDto::fromEntity)
        .toList();
    return ResponseEntity.status(200).body(cropDto);
  }

  /**
   cria a rota para criar a associação entre uma plantação e um fertilizante.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> createAssociation(@PathVariable("cropId") long cropId,
                                                  @PathVariable("fertilizerId") long fertilizerId) {

    Crop crop = cropService.findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    fertilizerService.associateCropAndFertilizer(fertilizer, crop);

    return ResponseEntity.status(201).body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   cria a rota para listar os fertilizante associados a uma plantação.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getFertilizerListByCropId(@PathVariable long cropId)
      throws CropNotFoundException {
    Crop crop = cropService.findById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();
    // fertilizers.stream().map(FertilizerDto::fromEntity).toList();
    
    return ResponseEntity.status(200).body(fertilizers);
  }
}

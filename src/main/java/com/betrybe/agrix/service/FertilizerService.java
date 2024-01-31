package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 Controlador da classe Fertilizer.
 */
@Service
public class FertilizerService {
  private FertilizerRepository fertilizerRepository;
  private CropRepository cropRepository;

  /**
   construtor da classe Fertilizer.
   */

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository,
                           CropRepository cropRepository) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }

  /**
   insere um fertilizer no bd.
   */

  public Fertilizer insertFertilizer(Fertilizer fertilizer) {

    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  /**
   encontra um fertilizer pelo id.
   */

  public Fertilizer findById(long id) throws FertilizerNotFoundException {
    Optional<Fertilizer> dbFertilizer = fertilizerRepository.findById(id);

    if (dbFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    return dbFertilizer.get();

  }

  /**
   cria a rota para listar os fertilizante associados a uma plantação.
   */
  public Fertilizer associateCropAndFertilizer(Fertilizer fertilizer, Crop crop)
      throws FertilizerNotFoundException, CropNotFoundException {

    fertilizer.getCropList().add(crop);
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);

    return fertilizerRepository.save(fertilizer);
  }
}

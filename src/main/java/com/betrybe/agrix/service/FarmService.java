package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 Classe de serviço da fazenda.
 */

@Service

public class FarmService {
  private FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   encontra a fazenda pelo id.
   */

  public Farm findById(long id) throws FarmNotFoundException {
    Optional<Farm> dbFarm = farmRepository.findById(id);

    if (dbFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return dbFarm.get();

  }
}

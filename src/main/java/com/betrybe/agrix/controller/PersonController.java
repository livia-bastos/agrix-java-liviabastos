package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 Controlador da Classe Person.
 */

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  /**
   Construtor da classe.
   */

  @Autowired
  public PersonController(PersonService personService) { this.personService = personService; }

  /**
   insere uma pessoa no bd.
   */

  @PostMapping
  public ResponseEntity<PersonDto> insertPerson(@RequestBody Person person) {
    Person savedPerson = personService.create(person);
    PersonDto personDto = PersonDto.fromEntity(savedPerson);
    return ResponseEntity.status(201).body(personDto);
  }
}

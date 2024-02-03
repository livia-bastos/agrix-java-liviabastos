package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 Dto da classe Person.
 */

public record PersonDto(
    Long id,
    String username,
    Role role

) {

  /**
   Método que transforma a classe em Dto.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
    person.getId(),
    person.getUsername(),
    person.getRole()
    );

  }
}

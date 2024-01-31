package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.entities.Person;
import com.betrybe.agrix.security.Role;

public record PersonDto (
  Long id,
  String username,
  Role role

){
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
    person.getId(),
    person.getUsername(),
    person.getRole()
    );

  }
}

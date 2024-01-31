package com.betrybe.agrix.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;


/**
 Classe Farm.
 */

@Entity
@Table(name = "farm")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double size;

  public List<Crop> getCropList() {
    return cropList;
  }

  public void setCropList(List<Crop> cropList) {
    this.cropList = cropList;
  }

  @OneToMany(mappedBy = "farm")
  private List<Crop> cropList = new ArrayList<>();

  public Farm() {

  }
  /**
   Construtor da Classe Farm.
   */

  public Farm(Long id, String name, double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }
}

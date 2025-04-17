package com.example.mvc_demo.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntityAudit {

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role() {

  }

  public Role(ERole name) {
    this.name = name;
  }

  public ERole getName() {
    return name;
  }

  public void setName(ERole name) {
    this.name = name;
  }
}
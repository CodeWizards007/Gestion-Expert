package com.example.expert.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column()
  private com.example.expert.entity.ERole name;

  public Role() {

  }

  public Role(com.example.expert.entity.ERole name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public com.example.expert.entity.ERole getName() {
    return name;
  }

  public void setName(com.example.expert.entity.ERole name) {
    this.name = name;
  }
}

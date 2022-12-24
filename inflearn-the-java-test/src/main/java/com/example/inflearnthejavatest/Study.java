package com.example.inflearnthejavatest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Study {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private int limit;
  private StudyStatus status = StudyStatus.DRAFT;

  private String name;
  @OneToOne
  @JoinColumn(name = "owner_id")
  private Member owner;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Study(int limit) {
    this.limit = limit;
  }

  public Study(int limit, String name) {
    this.limit = limit;
    this.name = name;
  }

  public int getLimit() {
    return limit;
  }

  public StudyStatus getStatus() {
    return status;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Study{" +
        "limit=" + limit +
        ", status=" + status +
        ", name='" + name + '\'' +
        '}';
  }

  public Member getOwner() {
    return owner;
  }

  public void setOwner(Member member) {
    this.owner = member;
  }

  protected Study() {
  }
}

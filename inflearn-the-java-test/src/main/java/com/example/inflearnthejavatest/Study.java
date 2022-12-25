package com.example.inflearnthejavatest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "study")
public class Study {

  @Id @GeneratedValue
  private Long id;

  private int limitCount;
  private StudyStatus status = StudyStatus.DRAFT;

  private String name;
  private Long ownerId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Study(int limitCount) {
    this.limitCount = limitCount;
  }

  public Study(int limitCount, String name) {
    this.limitCount = limitCount;
    this.name = name;
  }

  public int getLimitCount() {
    return limitCount;
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
        "limit=" + limitCount +
        ", status=" + status +
        ", name='" + name + '\'' +
        '}';
  }

  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  protected Study() {
  }
}

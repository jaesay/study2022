package com.example.inflearnthejavatest._01_junit5;

public class Study {

  private int limit;
  private StudyStatus status = StudyStatus.DRAFT;

  private String name;

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
}

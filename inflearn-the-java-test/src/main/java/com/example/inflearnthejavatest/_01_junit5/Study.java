package com.example.inflearnthejavatest._01_junit5;

public class Study {

  private int limit;
  private StudyStatus status = StudyStatus.DRAFT;

  public Study(int limit) {
    this.limit = limit;
  }

  public int getLimit() {
    return limit;
  }

  public StudyStatus getStatus() {
    return status;
  }
}

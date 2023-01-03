package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MoimTest {

  @Test
  void isFullTest() {
    Moim moim = new Moim();
    moim.maxNumberOfAttendees = 100;
    moim.numberOfEnrollment = 10;
    Assertions.assertFalse(moim.isEnrollmentFull());
  }
}
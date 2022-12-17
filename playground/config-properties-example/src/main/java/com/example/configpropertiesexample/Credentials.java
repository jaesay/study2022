package com.example.configpropertiesexample;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class Credentials {
  @Length(max = 4, min = 1)
  private String authMethod;
  private String username;
  private String password;
}

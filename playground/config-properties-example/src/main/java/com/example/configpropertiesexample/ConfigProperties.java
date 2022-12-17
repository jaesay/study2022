package com.example.configpropertiesexample;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mail")
@Getter
@Setter
public class ConfigProperties {

  @NotBlank
  private String host;
  @Min(1025)
  @Max(65536)
  private int port;
  @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
  private String from;
  private List<String> defaultRecipients;
  private Map<String, String> additionalHeaders;
  private Credentials credentials;
}

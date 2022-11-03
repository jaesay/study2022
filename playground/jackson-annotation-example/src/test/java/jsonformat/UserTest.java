package jsonformat;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  public void serializeLocalDateTimeTest() throws JsonProcessingException {
    User user = new User(
        "Jay",
        "Sridhar",
        LocalDateTime.of(2022, 11, 4, 0, 0, 0)
    );

    String result = this.objectMapper.writeValueAsString(user);

    assertThat(result).contains("2022-11-04T00:00:00");
  }

  @Test
  public void deserializeLocalDateTimeTest() throws JsonProcessingException {
    String userJson = "{\"firstName\":\"Jay\",\"lastName\":\"Sridhar\",\"createdDateTime\":\"2022-11-04T00:00:00\"}";

    User user = this.objectMapper.readValue(userJson, User.class);

    assertThat(user.getCreatedDateTime()).isEqualTo(LocalDateTime.of(2022, 11, 4, 0, 0, 0));
  }
}
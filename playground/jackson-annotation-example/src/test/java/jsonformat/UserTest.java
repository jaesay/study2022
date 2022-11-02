package jsonformat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

class UserTest {

  @Test
  public void serializeLocalDateTimeTest() throws JsonProcessingException {

    User user = new User("Jay", "Sridhar");

    String result = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(user);

    System.out.println(result);
  }

  @Test
  public void deserializeLocalDateTimeTest() throws JsonProcessingException {
    String userJson = "{\"firstName\":\"Jay\",\"lastName\":\"Sridhar\",\"createdDateTime\":\"2022-11-02T23:29:10\"}";

    User user = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(userJson, User.class);

    System.out.println(user);
  }
}
package com.example.d2jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

//  @Bean
  public Jackson2ObjectMapperBuilderCustomizer objectMapperConfig() {
    return builder -> {
      builder.deserializerByType(UserType.class, new CodeEnumDeserializer(UserType.class));
    };
  }

  @Bean
  public Module codeEnumModule() {
    return new Module() {
      @Override
      public String getModuleName() {
        return "codeEnum";
      }

      @Override
      public Version version() {
        return Version.unknownVersion();
      }

      @Override
      public void setupModule(SetupContext context) {
        context.addDeserializers(new Deserializers.Base() {
          final Map<ClassKey, JsonDeserializer<?>> cache = new ConcurrentHashMap<>();

          @Override
          public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) {
            if (CodeEnum.class.isAssignableFrom(type)) {
              JsonDeserializer<?> enumDeserializer = new CodeEnumDeserializer(type);
              addDeserializer(type, enumDeserializer);
              return enumDeserializer;
            }
            return null;
          }

          @Override
          public boolean hasDeserializerFor(DeserializationConfig config, Class<?> valueType) {
            return cache.containsKey(new ClassKey(valueType));
          }

          public void addDeserializer(Class<?> forClass, JsonDeserializer<?> deserializer) {
            ClassKey key = new ClassKey(forClass);
            cache.put(key, deserializer);
          }
        });
      }
    };
  }
}

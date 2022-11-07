package polymorphic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Zoo {
  private Animal animal;

  private Zoo() {
  }

  public Zoo(Animal animal) {
    this.animal = animal;
  }

  public Animal getAnimal() {
    return animal;
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "type")
  @JsonSubTypes({
      @JsonSubTypes.Type(value = Dog.class, name = "dog"),
      @JsonSubTypes.Type(value = Cat.class, name = "cat")
  })
  public static class Animal {
    private String name;

    private Animal() {
    }

    public Animal(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  @JsonTypeName("dog")
  public static class Dog extends Animal {
    private double barkVolume;

    private Dog() {
    }

    public Dog(String name) {
      super(name);
    }

    public double getBarkVolume() {
      return barkVolume;
    }
  }

  @JsonTypeName("cat")
  public static class Cat extends Animal {
    private boolean likesCream;
    private int lives;

    private Cat() {
    }

    public Cat(String name) {
      super(name);
    }

    public boolean isLikesCream() {
      return likesCream;
    }

    public int getLives() {
      return lives;
    }
  }
}

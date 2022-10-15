package com.example.d2jackson;

//@JsonDeserialize(using = ItemDeserializer.class)
public class Item {
  private final int id;
  private final String itemName;
  private final User owner;

  public Item(int id, String itemName, User owner) {
    this.id = id;
    this.itemName = itemName;
    this.owner = owner;
  }

  public int getId() {
    return id;
  }

  public String getItemName() {
    return itemName;
  }

  public User getOwner() {
    return owner;
  }

  @Override
  public String toString() {
    return "Item{" +
        "id=" + id +
        ", itemName='" + itemName + '\'' +
        ", owner=" + owner +
        '}';
  }
}

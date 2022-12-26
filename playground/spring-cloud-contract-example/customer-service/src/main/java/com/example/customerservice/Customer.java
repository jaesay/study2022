package com.example.customerservice;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@ToString
public class Customer {

  @Id @GeneratedValue
  private Long id;
  private String name;

  protected Customer() {
  }

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Customer customer = (Customer) o;
    return id != null && Objects.equals(id, customer.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

package com.udemy.event.driven.cqrs.webapp.intro.core.data;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductLookup")
public class ProductLookupEntity implements Serializable {

  @Id
  private String productId;

  @Column(unique = true)
  private String title;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    ProductLookupEntity that = (ProductLookupEntity) o;
    return productId != null && Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

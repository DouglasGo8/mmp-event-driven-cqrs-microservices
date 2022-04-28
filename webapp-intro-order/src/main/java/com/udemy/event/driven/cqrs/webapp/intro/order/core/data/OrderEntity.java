package com.udemy.event.driven.cqrs.webapp.intro.order.core.data;

import com.udemy.event.driven.cqrs.webapp.intro.order.core.model.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@Table(name = "orders")
@RequiredArgsConstructor
public class OrderEntity implements Serializable {

  @Id
  @Column(unique = true)
  public String orderId;
  //
  private int quantity;
  private String userId;
  private String productId;
  private String addressId;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
}

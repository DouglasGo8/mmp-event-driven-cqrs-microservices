package com.udemy.event.driven.cqrs.webapp.intro.order.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface OrdersRepository extends JpaRepository<OrderEntity, String> {
}

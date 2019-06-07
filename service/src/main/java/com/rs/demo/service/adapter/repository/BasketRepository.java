package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, String> {

    List<Basket> findByUserId(final UUID userId);

    Basket save(final Basket basket);

}

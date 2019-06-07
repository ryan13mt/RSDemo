package com.rs.demo.service.port;

import com.rs.demo.service.domain.models.Basket;

import java.util.List;
import java.util.UUID;

public interface BasketDao {

    List<Basket> findByUserId(final UUID userId);

    Basket save(final Basket basket);

}

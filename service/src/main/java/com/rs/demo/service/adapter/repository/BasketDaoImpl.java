package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.port.BasketDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@Validated
@AllArgsConstructor
public class BasketDaoImpl implements BasketDao {

    private final BasketRepository basketRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Basket> findByUserId(final UUID userId) {
        return basketRepository.findByUserId(userId);
    }

    @Override
    public Basket save(final Basket basket) {
        return basketRepository.save(basket);
    }
}

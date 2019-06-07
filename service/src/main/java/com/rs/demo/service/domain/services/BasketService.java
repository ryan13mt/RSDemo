package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.port.BasketDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class BasketService {

    private BasketDao basketDao;

    public List<Basket> add(@NotNull @Valid final List<Basket> newBasketProducts) {
        final UUID userId = newBasketProducts.get(0).getUserId();
        final List<Basket> basketList = basketDao.findByUserId(userId);

        newBasketProducts.forEach(entry -> {
            if (basketList.contains(entry)) {
                throw new IllegalStateException("Item has already been purchased!");
            }
            basketDao.save(entry);
        });

        return basketDao.findByUserId(userId);
    }

}

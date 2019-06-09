package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.port.BasketDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class BasketService {

    private BasketDao basketDao;

    public List<Basket> add(final List<Basket> newBasketProducts) {
        final UUID userId = newBasketProducts.get(0).getUserId();
        final List<Basket> basketList = basketDao.findByUserId(userId);

        verifyListsHaveNoEntriesInCommon(newBasketProducts, basketList);

        newBasketProducts.forEach(entry -> basketDao.save(entry));

        return basketDao.findByUserId(userId);
    }

    private void verifyListsHaveNoEntriesInCommon(final List<Basket> newBasketProducts, final List<Basket> basketList) {
        newBasketProducts.forEach(newEntry -> basketList.forEach(existingEntry -> {
            if (existingEntry.getProductId().equals(newEntry.getProductId())) {
                throw new IllegalStateException("Item " + newEntry.getProductId() + " has already been purchased!");
            }
        }));
    }

}

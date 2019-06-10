package com.rs.demo.service.adapter.transformer;

import com.rs.demo.service.adapter.dto.BasketDto;
import com.rs.demo.service.domain.models.Basket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BasketTransformer {

    public BasketDto transformToDto(final Basket basket) {
        log.debug("Transforming Basket to BasketDto");
        return new BasketDto(basket.getProductId(),
                basket.getUserId());
    }

    public Basket transformFromDto(final BasketDto basket) {
        log.debug("Transforming BasketDto to Basket");
        return new Basket(basket.getProductId(),
                basket.getUserId());
    }

    public List<BasketDto> transformListToDtoList(List<Basket> basketList) {
        log.debug("Transforming list of Basket to list of BasketDto");
        return basketList.stream().map(this::transformToDto).collect(Collectors.toList());
    }

    public List<Basket> transformDtoListToList(List<BasketDto> basketDtoList) {
        log.debug("Transforming list of BasketDto to list of Basket");
        return basketDtoList.stream().map(this::transformFromDto).collect(Collectors.toList());
    }
}

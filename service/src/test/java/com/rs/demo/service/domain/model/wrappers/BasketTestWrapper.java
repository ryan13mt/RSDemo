package com.rs.demo.service.domain.model.wrappers;

import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class BasketTestWrapper {

    private UUID id;
    private UUID productId;
    private UUID userId;

    public static BasketTestWrapper buildValid(final UUID productId, final UUID userId) {
        return BasketTestWrapper.builder()
                .productId(productId)
                .userId(userId)
                .build();
    }

    public Basket unwrap() {
        return new Basket(this.id,
                          this.productId,
                          this.userId);
    }

}

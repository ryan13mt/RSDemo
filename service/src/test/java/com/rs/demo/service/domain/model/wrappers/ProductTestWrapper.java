package com.rs.demo.service.domain.model.wrappers;

import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class ProductTestWrapper {

    private UUID id;
    private String name;
    private ProductType type;
    private UUID userId;

    public static ProductTestWrapper buildValid(final String name, final ProductType type, final UUID userId) {
        return ProductTestWrapper.builder()
                .name(name)
                .type(type)
                .userId(userId)
                .build();
    }

    public Product unwrap() {
        return new Product(this.id,
                           this.name,
                           this.type,
                           this.userId);
    }

}

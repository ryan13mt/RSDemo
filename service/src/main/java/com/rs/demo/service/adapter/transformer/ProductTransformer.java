package com.rs.demo.service.adapter.transformer;

import com.rs.demo.service.adapter.dto.ProductDto;
import com.rs.demo.service.domain.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductTransformer {

    public ProductDto transformToDto(final Product product) {
        log.debug("Transforming Product to ProductDto");
        return new ProductDto(product.getId(),
                              product.getName(),
                              product.getType(),
                              product.getUserId());
    }

    public List<ProductDto> transformListToDtoList(List<Product> productList) {
        log.debug("Transforming list of Product to list of ProductDto");
        return productList.stream().map(this::transformToDto).collect(Collectors.toList());
    }

}

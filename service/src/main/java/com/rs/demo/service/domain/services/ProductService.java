package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductFilter;
import com.rs.demo.service.port.ProductDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ProductService {

    private ProductDao productDao;

    public List<Product> find(final ProductFilter filterDto) {
        if (filterDto == null) {
            return productDao.findAll();
        }
        return productDao.find(filterDto);
    }

}

package com.rs.demo.service.port;

import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductFilter;

import java.util.List;

public interface ProductDao {

    List<Product> find(final ProductFilter filter);

    List<Product> findAll();
}

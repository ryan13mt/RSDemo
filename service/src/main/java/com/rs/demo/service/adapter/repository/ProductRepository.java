package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByNameContainingAndTypeEquals(final String name, final ProductType type);

}

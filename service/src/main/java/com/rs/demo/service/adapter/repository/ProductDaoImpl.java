package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductFilter;
import com.rs.demo.service.port.ProductDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Component
@Validated
@AllArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> find(final ProductFilter filter) {
        return productRepository.findAllByNameContainingIgnoreCaseAndTypeEquals(filter.getName() == null ? "" : filter.getName(), filter.getType());
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

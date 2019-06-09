package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductFilter;
import com.rs.demo.service.domain.models.ProductType;
import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductDaoTest {

    @Autowired
    private ProductDao sut;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAll_shouldFindAllEntities() {
        final User user1 = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final User user2 = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final Product product1 = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "productNameOne", ProductType.GAMES, user1.getId()));
        final Product product2 = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "productNameTwo", ProductType.GAMES, user2.getId()));

        final List<Product> retrievedProducts = sut.findAll();
        assertThat(retrievedProducts).contains(product1, product2);
    }

    @Test
    public void find_shouldFindFilteredEntities() {
        final User user1 = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final User user2 = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final Product product1 = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "productNameOne", ProductType.GAMES, user1.getId()));
        final Product product2 = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "productNameTwo", ProductType.GAMES, user2.getId()));

        final List<Product> retrievedProducts = sut.find(new ProductFilter("productNameOne", ProductType.GAMES));
        assertThat(retrievedProducts).contains(product1);
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public ProductDao productDao(final ProductRepository productRepository, final EntityManager entityManager) {
            return new ProductDaoImpl(productRepository, entityManager);
        }
    }

}

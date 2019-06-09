package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductType;
import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.BasketDao;
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
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BasketDaoTest {

    @Autowired
    private BasketDao sut;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByUserId_shouldFindExactEntities() {
        final User user = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final Product product = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "test", ProductType.GAMES, user.getId()));
        final Basket basket = entityManager.persistAndFlush(new Basket(product.getId(), user.getId()));

        final List<Basket> retrievedBasket = sut.findByUserId(basket.getUserId());
        assertThat(retrievedBasket).containsExactly(basket);
    }

    @Test
    public void findByUserId_multipleUsers_shouldFindExactEntities() {
        final User user1 = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final User user2 = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final Product product1 = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "productNameOne", ProductType.GAMES, user1.getId()));
        final Product product2 = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "productNameTwo", ProductType.GAMES, user2.getId()));
        final Basket basket1 = entityManager.persistAndFlush(new Basket(product1.getId(), user1.getId()));
        final Basket basket2 = entityManager.persistAndFlush(new Basket(product2.getId(), user2.getId()));

        final List<Basket> retrievedBasket = sut.findByUserId(basket1.getUserId());
        assertThat(retrievedBasket).containsExactly(basket1);
    }

    @Test
    public void save_shouldSaveEntity() {
        final User user = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));
        final Product product = entityManager.persistAndFlush(new Product(UUID.randomUUID(), "test", ProductType.GAMES, user.getId()));
        final Basket basket = entityManager.persistAndFlush(new Basket(product.getId(), user.getId()));

        final Basket retrievedBasket = sut.save(basket);
        assertThat(retrievedBasket).isEqualToComparingFieldByField(basket);
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public BasketDao basketDao(final BasketRepository basketRepository, final EntityManager entityManager) {
            return new BasketDaoImpl(basketRepository, entityManager);
        }
    }

}

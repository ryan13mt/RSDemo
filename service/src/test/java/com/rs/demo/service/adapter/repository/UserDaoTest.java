package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.model.wrappers.UserTestWrapper;
import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.UserDao;
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

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserDaoTest {

    @Autowired
    private UserDao sut;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getById_shouldFindExactEntity() {
        final User user = entityManager.persistAndFlush(new User(UUID.randomUUID(), "userName"));

        final User retrievedUser = sut.get(user.getId());
        assertThat(retrievedUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    public void getById_shouldReturnNull() {
        final User retrievedUser = sut.get(UUID.randomUUID());
        assertThat(retrievedUser).isNull();
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public UserDao userDao(final UserRepository userRepository, final EntityManager entityManager) {
            return new UserDaoImpl(userRepository, entityManager);
        }
    }

}

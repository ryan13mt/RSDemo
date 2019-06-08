package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Slf4j
@Component
@Validated
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User get(final UUID id) {
        return userRepository.getById(id);
    }
}

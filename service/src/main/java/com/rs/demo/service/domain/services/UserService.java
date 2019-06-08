package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class UserService {

    private UserDao userDao;

    public User get(final UUID id) {
        return userDao.get(id);
    }

}

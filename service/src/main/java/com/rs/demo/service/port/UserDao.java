package com.rs.demo.service.port;

import com.rs.demo.service.domain.models.User;

import java.util.UUID;

public interface UserDao {

    User get(final UUID id);
}

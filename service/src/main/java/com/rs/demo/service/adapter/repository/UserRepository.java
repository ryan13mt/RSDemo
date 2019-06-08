package com.rs.demo.service.adapter.repository;

import com.rs.demo.service.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    User getById(final UUID id);

}

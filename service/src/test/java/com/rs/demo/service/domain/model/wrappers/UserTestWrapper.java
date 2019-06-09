package com.rs.demo.service.domain.model.wrappers;

import com.rs.demo.service.domain.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
public class UserTestWrapper {

    private UUID id;
    private String userName;

    public static UserTestWrapper buildValid(final String userName) {
        return UserTestWrapper.builder()
                .userName(userName)
                .build();
    }

    public User unwrap() {
        return new User(this.id,
                        this.userName);
    }

}

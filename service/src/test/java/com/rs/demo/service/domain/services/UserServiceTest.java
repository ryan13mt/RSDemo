package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.model.wrappers.UserTestWrapper;
import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService sut;

    @Test
    public void getUser_userFound_returnedSuccessfully() {
        final User user = UserTestWrapper.buildValid("testUserName").unwrap();
        given(userDao.get(any())).willReturn(user);

        final User retrievedUser = sut.get(UUID.randomUUID());

        assertThat(retrievedUser).isEqualTo(user);

        verify(userDao, times(1)).get(any());
    }

    @Test
    public void getUser_userNotFound_returnNull() {
        given(userDao.get(any())).willReturn(null);

        final User retrievedUser = sut.get(UUID.randomUUID());

        assertThat(retrievedUser).isEqualTo(null);

        verify(userDao, times(1)).get(any());
    }

}

package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.model.wrappers.BasketTestWrapper;
import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.port.BasketDao;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @Mock
    private BasketDao basketDao;

    @InjectMocks
    private BasketService sut;

    @Test(expected = IllegalStateException.class)
    public void add_productAlreadyBought_throwIllegalStateException() {
        final Basket basket = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        given(basketDao.findByUserId(any())).willReturn(Collections.singletonList(basket));

        sut.add(Collections.singletonList(basket));

        verify(basketDao, times(1)).findByUserId(any());
        verify(basketDao, times(0)).save(any());
    }

    @Test
    public void add_newProduct_addSuccessful() {
        final Basket basket1 = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        final Basket basket2 = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        given(basketDao.findByUserId(any())).willReturn(Collections.singletonList(basket1));

        sut.add(Collections.singletonList(basket2));

        verify(basketDao, times(2)).findByUserId(any());
        verify(basketDao, times(1)).save(any());
    }


}

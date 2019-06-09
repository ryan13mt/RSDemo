package com.rs.demo.service.domain.services;

import com.rs.demo.service.domain.model.wrappers.ProductTestWrapper;
import com.rs.demo.service.domain.model.wrappers.UserTestWrapper;
import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductFilter;
import com.rs.demo.service.domain.models.ProductType;
import com.rs.demo.service.domain.models.User;
import com.rs.demo.service.port.BasketDao;
import com.rs.demo.service.port.ProductDao;
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
public class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService sut;

    @Test
    public void findProducts_filterProvided_findSuccessful() {
        final Product product = ProductTestWrapper.buildValid("testProductName", ProductType.GAMES, UUID.randomUUID()).unwrap();
        given(productDao.find(any())).willReturn(Collections.singletonList(product));

        final List<Product> retrievedProduct = sut.find(new ProductFilter());

        assertThat(retrievedProduct).isEqualTo(Collections.singletonList(product));

        verify(productDao, times(1)).find(any());
    }

    @Test
    public void findProducts_filterNotProvided_findAllSuccessful() {
        final Product product = ProductTestWrapper.buildValid("testProductName", ProductType.GAMES, UUID.randomUUID()).unwrap();
        given(productDao.findAll()).willReturn(Collections.singletonList(product));

        final List<Product> retrievedProduct = sut.find(null);

        assertThat(retrievedProduct).isEqualTo(Collections.singletonList(product));

        verify(productDao, times(1)).findAll();
    }


}

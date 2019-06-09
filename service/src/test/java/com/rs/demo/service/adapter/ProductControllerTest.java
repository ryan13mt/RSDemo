package com.rs.demo.service.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rs.demo.service.adapter.transformer.BasketTransformer;
import com.rs.demo.service.adapter.transformer.ProductTransformer;
import com.rs.demo.service.domain.model.wrappers.BasketTestWrapper;
import com.rs.demo.service.domain.model.wrappers.ProductTestWrapper;
import com.rs.demo.service.domain.model.wrappers.UserTestWrapper;
import com.rs.demo.service.domain.models.Basket;
import com.rs.demo.service.domain.models.Product;
import com.rs.demo.service.domain.models.ProductFilter;
import com.rs.demo.service.domain.models.ProductType;
import com.rs.demo.service.domain.services.BasketService;
import com.rs.demo.service.domain.services.ProductService;
import com.rs.demo.service.domain.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BasketTransformer basketTransformer;
    @Autowired
    private ProductTransformer productTransformer;

    @MockBean
    private BasketService basketService;
    @MockBean
    private ProductService productService;
    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getProducts() throws Exception {
        final Product product = ProductTestWrapper.buildValid("testProductName", ProductType.GAMES, UUID.randomUUID()).unwrap();

        when(productService.find(any(ProductFilter.class))).thenReturn(Collections.singletonList(product));

        this.mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk());

        verify(productService, times(1)).find(null);
    }

    @Test
    public void add_userDoesNotExist_throwsIllegalStateException() throws Exception {
        final Basket basket = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        when(basketService.add(any())).thenReturn(Collections.singletonList(basket));

        try {
            this.mockMvc.perform(put("/product")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(Collections.singletonList(basketTransformer.transformToDto(basket)))))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
        }

        verify(basketService, times(0)).add(any());
    }

    @Test
    public void add_multipleUsers_throwsIllegalStateException() throws Exception {
        final Basket basket1 = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        final Basket basket2 = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        final List<Basket> basketList = Arrays.asList(basket1, basket2);
        when(basketService.add(any())).thenReturn(basketList);
        when(userService.get(any())).thenReturn(UserTestWrapper.buildValid("userName").unwrap());

        try {
            this.mockMvc.perform(put("/product")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(basketTransformer.transformListToDtoList(basketList))))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
        }

        verify(basketService, times(0)).add(any());
    }

    @Test
    public void add_multipleOfSameItem_throwsIllegalStateException() throws Exception {
        final Basket basket = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        final List<Basket> basketList = Arrays.asList(basket, basket);
        when(basketService.add(any())).thenReturn(basketList);
        when(userService.get(any())).thenReturn(UserTestWrapper.buildValid("userName").unwrap());

        try {
            this.mockMvc.perform(put("/product")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(basketTransformer.transformListToDtoList(basketList))))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
        }

        verify(basketService, times(0)).add(any());
    }

    @Test
    public void add_success() throws Exception {
        final Basket basket = BasketTestWrapper.buildValid(UUID.randomUUID(), UUID.randomUUID()).unwrap();
        when(basketService.add(any())).thenReturn(Collections.singletonList(basket));
        when(userService.get(any())).thenReturn(UserTestWrapper.buildValid("userName").unwrap());

        this.mockMvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(Collections.singletonList(basketTransformer.transformToDto(basket)))))
                .andExpect(status().isOk());

        verify(basketService, times(1)).add(any());
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public ProductTransformer productTransformer() {
            return new ProductTransformer();
        }

        @Bean
        public BasketTransformer basketTransformer() {
            return new BasketTransformer();
        }
    }

}

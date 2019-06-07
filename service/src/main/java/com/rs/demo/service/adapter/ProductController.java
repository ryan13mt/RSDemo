package com.rs.demo.service.adapter;

import com.rs.demo.service.adapter.dto.BasketDto;
import com.rs.demo.service.adapter.dto.ProductDto;
import com.rs.demo.service.adapter.transformer.BasketTransformer;
import com.rs.demo.service.adapter.transformer.ProductTransformer;
import com.rs.demo.service.domain.models.ProductFilter;
import com.rs.demo.service.domain.services.BasketService;
import com.rs.demo.service.domain.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(path = "/product", produces = APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

    private BasketService basketService;
    private ProductService productService;
    private ProductTransformer productTransformer;
    private BasketTransformer basketTransformer;

    @ApiOperation(
            value = "Get a list of products matching with the filtering criteria provided. If no filter is present, return all products.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "The request has been successfully processed and a list of applicable products will be presented if any exists."),
            @ApiResponse(code = 400, message = "The request is rejected because it was not understood by the server due to malformed syntax. Do not repeat the request without modifications."),
            @ApiResponse(code = 422, message = "The request is rejected because the server was unable to process the given request due to semantic validations. Do not repeat the request without modifications.")
    })
    @GetMapping
    public List<ProductDto> getProducts(@Nullable @RequestBody @Valid final ProductFilter filter) {
        log.trace("Received request to findByUserId product list filtered by {}", filter);
        return productTransformer.transformListToDtoList(productService.find(filter));
    }

    @ApiOperation(
            value = "Update basket.")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 201, message = "The request has been successfully processed and the basket has been updated."),
            @ApiResponse(code = 400, message = "The request is rejected because it was not understood by the server due to malformed syntax. Do not repeat the request without modifications."),
            @ApiResponse(code = 422, message = "The request is rejected because the server was unable to process the given request due to semantic validations. Do not repeat the request without modifications.")
    })
    @PutMapping
    public List<BasketDto> addToBasket(@RequestBody @NotNull @Valid final List<BasketDto> basketDto) {
        log.trace("Received request to add basket with these details: {}", basketDto);
        if (basketDto.stream().map(BasketDto::getUserId).distinct().limit(2).count() <= 1) {
            throw new IllegalStateException("List provided contains buying products for multiple users!");
        }
        return basketTransformer.transformListToDtoList(basketService.add(basketTransformer.transformDtoListToList(basketDto)));
    }

}

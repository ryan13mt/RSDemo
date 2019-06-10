package com.rs.demo.service.adapter.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BasketDto {

    @NotNull
    private UUID productId;

    @NotNull
    private UUID userId;

}
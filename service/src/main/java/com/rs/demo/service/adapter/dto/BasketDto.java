package com.rs.demo.service.adapter.dto;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BasketDto {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    private UUID productId;

    @NotNull
    private UUID userId;

}
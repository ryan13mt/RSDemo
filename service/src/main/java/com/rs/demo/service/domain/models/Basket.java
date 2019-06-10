package com.rs.demo.service.domain.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    private UUID productId;

    @NotNull
    private UUID userId;

    public Basket(@NotNull final UUID productId,
                  @NotNull final UUID userId) {
        this.productId = productId;
        this.userId = userId;
    }

}

package com.rs.demo.service.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Basket {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    private UUID productId;

    @NotNull
    private UUID userId;

}

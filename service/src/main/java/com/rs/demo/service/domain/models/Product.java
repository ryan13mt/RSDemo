package com.rs.demo.service.domain.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    @NotEmpty
    @Length(max = 30)
    @Pattern(regexp = "^[-a-zA-Z]+")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @NotNull
    @Setter(AccessLevel.NONE)
    private UUID userId; //Assuming this is the merchant

}

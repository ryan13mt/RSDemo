package com.rs.demo.service.adapter.dto;

import com.rs.demo.service.domain.models.ProductType;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDto {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    @NotEmpty
    @Length(max = 30)
    @Pattern(regexp = "[a-zA-Z]")
    private String name;

    @NotNull
    private ProductType type;

    @NotNull
    @Setter(AccessLevel.NONE)
    private UUID userId; //Assuming this is the merchant

}
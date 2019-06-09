package com.rs.demo.service.domain.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
public class User {

    @Id
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    @Length(max = 40)
    private String userName;

}

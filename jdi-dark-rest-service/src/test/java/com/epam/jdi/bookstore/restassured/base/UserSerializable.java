package com.epam.jdi.bookstore.restassured.base;

import com.epam.jdi.bookstore.model.IdentifiedEntity;
import com.epam.jdi.bookstore.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserSerializable implements IdentifiedEntity {

    private Long id;
    private String name;
    private String password;
    private boolean enabled;
    private List<Role> roles;
}

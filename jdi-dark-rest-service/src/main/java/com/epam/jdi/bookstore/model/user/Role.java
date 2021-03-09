package com.epam.jdi.bookstore.model.user;

import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends DataClass<Role> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "User role ID",
            example = "2")
    @Column(name = "id", nullable = false, unique = true)
    public Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "name", nullable = false)
    public String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    transient public List<User> users;

    public Role() { }
    public Role(String name) {
        this.name = name;
    }
}

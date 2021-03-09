package com.epam.jdi.bookstore.model.user;

import com.epam.jdi.bookstore.security.UserPrincipal;
import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends DataClass<User> implements Serializable {
    public User() { }
    public User(UserPrincipal up) {
        id = up.id;
        email = up.name;
        password = up.password;
        roles = up.roles;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long id;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Schema(description = "User e-mail as login",
            example = "user@hotmail.com", required = true)
    @Column(name = "email", nullable = false)
    public String email;

    @NotBlank(message = "User password is mandatory")
    @Column(name = "password", nullable = false)
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;

    @Column(name = "enabled", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(
            name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id"))
    public List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    transient public List<Address> addresses;

}

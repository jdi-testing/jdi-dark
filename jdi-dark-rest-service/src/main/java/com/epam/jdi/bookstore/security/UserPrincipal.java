package com.epam.jdi.bookstore.security;

import com.epam.jdi.bookstore.model.user.Role;
import com.epam.jdi.bookstore.model.user.User;
import com.epam.jdi.tools.DataClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal extends DataClass<UserPrincipal> implements UserDetails {

    public UserPrincipal() {}
    public UserPrincipal(User user) {
        id = user.id;
        name = user.email;
        password = user.password;
        roles = user.roles;
    }
    public Long id;
    public String name;

    @JsonIgnore
    public String password;
    public List<Role> roles;

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return new User(this);
    }

}

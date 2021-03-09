package com.epam.jdi.bookstore.model.security;

import com.epam.jdi.tools.DataClass;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class Credentials extends DataClass<Credentials> {

    @NotBlank(message = "Email is mandatory")
    @Schema(description = "User e-mail as login",
            example = "admin@epam.com", required = true)
    public String email;

    @NotBlank(message = "Password is mandatory")
    @Schema(description = "User password",
            example = "1234", required = true)
    public String password;

}

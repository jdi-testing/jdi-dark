package com.epam.jdi.bookstore.controller;

import com.epam.jdi.bookstore.model.user.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@Tag(name = "role", description = "Roles API")
@RequestMapping("/roles")
public interface RoleApi {

    @Operation(summary = "Get all existing user roles", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Genre list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Role.class))))})
    @GetMapping(value = "", produces = {"application/json"})
    ResponseEntity<List<Role>> getRoles();

}

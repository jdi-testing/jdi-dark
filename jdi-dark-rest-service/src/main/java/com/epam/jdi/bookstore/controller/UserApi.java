package com.epam.jdi.bookstore.controller;

import com.epam.jdi.bookstore.model.security.Credentials;
import com.epam.jdi.bookstore.model.security.Token;
import com.epam.jdi.bookstore.model.user.Address;
import com.epam.jdi.bookstore.model.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@Tag(name = "user", description = "Users API")
public interface UserApi {

    @Operation(summary = "Register new user", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User role not found"),
            @ApiResponse(responseCode = "409", description = "User already exists")})
    @PostMapping(value = "/users/registration", consumes = {"application/json"})
    ResponseEntity<User> registerUser(@Parameter(description = "User object", required = true) @Valid @RequestBody User user);


    @Operation(summary = "Authorize user and generate token", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created", content = @Content(schema = @Schema(implementation = Token.class))),
            @ApiResponse(responseCode = "401", description = "Invalid login or password provided")})
    @PostMapping(value = "/users/auth", consumes = {"application/json"})
    ResponseEntity<Token> authenticateUser(@Parameter(description = "Credentials need to be provided to generate token") @Valid @RequestBody Credentials credentials);


    @Operation(summary = "Update an existing user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @PutMapping(value = "/users/{id}", produces = {"application/json"})
    ResponseEntity<User> updateUser(@Parameter(description = "ID of user to update", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id,
                                    @Parameter(description = "User object record") @RequestBody User user);


    @Operation(summary = "Get all existing users", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User records", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))})
    @GetMapping(value = "/users", produces = {"application/json"})
    ResponseEntity<List<User>> getUsers();


    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User record", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/{id}", produces = {"application/json"})
    ResponseEntity<User> getUserById(@Parameter(description = "ID of user to return", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @Operation(summary = "Get user by email", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User record", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/email/{email}", produces = {"application/json"})
    ResponseEntity<User> getUserByEmail(@Parameter(description = "Email of user to return", required = true)
                                        @NotBlank @PathVariable String email);


    @Operation(summary = "Delete user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @DeleteMapping(value = "/users/{id}")
    ResponseEntity<Void> deleteUser(@Parameter(description = "ID of user to be deleted", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @Operation(summary = "Restore user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been restored"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @PutMapping(value = "/users/{id}/restore")
    ResponseEntity<Void> restoreUser(@Parameter(description = "ID of user to be restored", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);

    @Operation(summary = "Get addresses of user by user ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Address.class)))),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/{user_id}/addresses", produces = {"application/json"})
    ResponseEntity<List<Address>> getAddresses(@Parameter(description = "ID of the user whose addresses to get", required = true)
                                               @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long user_id);


    @Operation(summary = "Add new address", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created", content = @Content(schema = @Schema(implementation = Address.class)))})
    @PostMapping(value = "/users/{user_id}/addresses}", consumes = {"application/json"})
    ResponseEntity<Void> addAddress(@Parameter(description = "ID of the user to add addresses to", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long user_id,
                                    @Parameter(description = "Address object", required = true) @Valid @RequestBody Address address);

}

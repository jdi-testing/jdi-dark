package com.epam.jdi.bookstore.controller.home;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class HealthController {

    @Operation(hidden = true)
    @GetMapping(value = "/status")
    public ResponseEntity<Void> getStatus() {
        return ok().build();
    }
}

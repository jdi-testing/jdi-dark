package com.epam.jdi.bookstore.controller.home;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Operation(hidden = true)
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/v3/swagger-ui.html";
    }
}

package com.artzh7.currere.controller.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant")
public class RestaurantMainController {
    @GetMapping()
    public String main() {
        return "restaurant/main";
    }
}

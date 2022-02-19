package com.artzh7.currere.controller.courier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courier")
public class CourierMainController {
    @GetMapping()
    public String main() {
        return "courier/main";
    }
}

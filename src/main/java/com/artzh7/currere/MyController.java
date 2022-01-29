package com.artzh7.currere;

import com.artzh7.currere.entity.Booking;
import com.artzh7.currere.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private BookingRepo bookingRepo;

    @GetMapping()
    public String main(Map<String, Object> model) {
        Iterable<Booking> bookings = bookingRepo.findAll();
        model.put("bookings", bookings);
        return "main";
    }

    @PostMapping()
    public String add(
            @RequestParam String restaurantName,
            @RequestParam String clientAddress,
            @RequestParam String clientPhoneNumber) {
        Booking booking = new Booking(restaurantName, clientAddress, clientPhoneNumber);
        bookingRepo.save(booking);
        return "redirect:/";
    }

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }
}

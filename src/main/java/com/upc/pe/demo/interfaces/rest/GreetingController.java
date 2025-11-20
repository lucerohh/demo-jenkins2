package com.upc.pe.demo.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greetings")
public class GreetingController {

    @GetMapping("{name}")
    public String greeting(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("{lastName}")
    public String greeting2(@PathVariable String lastName) {
        return "Hello " + lastName + "!";
    }
}

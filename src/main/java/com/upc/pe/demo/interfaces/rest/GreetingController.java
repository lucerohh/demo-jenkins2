package com.upc.pe.demo.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greetings")
public class GreetingController {

    @GetMapping("/name/{name}")
    public String greeting(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("{lastName}")
    public String greeting2(@PathVariable String lastName) {
        return "Hello " + lastName + "!";
    }

    @GetMapping("{nickname}")
    public String greeting3(@PathVariable String nickname) {
        return "Hello " + nickname + "!";
    }

    @GetMapping("{country}")
    public String greeting4(@PathVariable String country) {
        return "Hello " + country + "!";
    }

    @GetMapping("{age}")
    public String greeting5(@PathVariable String age) {
        return "Hello " + age + "!";
    }

    @GetMapping("{career}")
    public String greeting6(@PathVariable String career) {
        return "Hello " + career + "! You're incredible";
    }
}

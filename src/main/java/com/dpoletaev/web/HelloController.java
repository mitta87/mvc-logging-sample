package com.dpoletaev.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "hello")
public class HelloController {

    @GetMapping
    @ResponseBody
    public String hello() {
        return "hello!";
    }

    @PostMapping
    @ResponseBody
    public String post(@RequestBody String param) {
        return param + " received!";
    }
}


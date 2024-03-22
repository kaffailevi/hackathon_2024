package com.threedumbdevs.springapi.controllers;

import com.threedumbdevs.springapi.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping("/hello")
    public String test() {
        throw new NotFoundException("This is a test exception");
    }

}

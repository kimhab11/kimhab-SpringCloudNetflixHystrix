package com.example.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingControllerWithFeign {
    @Autowired
    GreetingClient.GreetingClientFallback greetingClient;

    @GetMapping("feign/{name}")
    public String get(@PathVariable String name){
        return greetingClient.greeting(name);
    }
}

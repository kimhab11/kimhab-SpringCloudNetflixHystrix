package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingService {
    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public String getGreeting(String name){
        return new RestTemplate().getForObject("http://localhost:8010/greeting/{username}" , String.class, name);
    }
    private String defaultGreeting(String username) {
        return "Hello User! this is fallback  ck method calling ";
    }
}

package com.example.feign;

import com.example.GreetingController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "rest-producer",
        url = "localhost:8010",
        fallback = GreetingClient.GreetingClientFallback.class
)
public interface GreetingClient {

    @Component
    public static class GreetingClientFallback {

        public String greeting(@PathVariable("username") String username) {
            return "Hello User!";
        }
    }
}

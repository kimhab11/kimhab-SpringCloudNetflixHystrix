package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GreetingController {
    @Autowired
    GreetingService greetingService;

    @GetMapping("/get-greeting/{username}")
    public String getGreeting(Model model, @PathVariable("username") String username) {
        model.addAttribute("greeting", greetingService.getGreeting(username));
        return "greeting-view";
    }

    @GetMapping("/get/{username}")
    public ModelAndView homePage(@PathVariable String username) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("a");
        mv.addObject("messages", greetingService.getGreeting(username));
        return mv;
    }


    @GetMapping("/{username}")
    public String getGreeting(@PathVariable("username") String username) {
        String s = greetingService.getGreeting(username);
        return s;
    }

}


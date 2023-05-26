package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NotifyController {
    @Autowired
    NotifyService notifyService;
    @GetMapping("/{matchId}")
    public Map<String, Object> getMatchNoti(@PathVariable int matchId){
        Map<String, Object> map = new HashMap<>();
        map.put("time",new Date());
        map.put("lastScore", notifyService.getMatchDetailById(matchId));
        map.put("nextUpdateIn", "60 sec");

        return map;
    }
}

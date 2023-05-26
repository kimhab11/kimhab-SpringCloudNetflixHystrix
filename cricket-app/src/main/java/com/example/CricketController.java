package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
public class CricketController {
    private Map<Integer, Properties> currentMatchData;

    @PostConstruct
    public void createMockData(){
        currentMatchData = new HashMap<>();
        Properties p1 = new Properties();
        p1.setProperty("Match Name","IND vs CAM");
        p1.setProperty("Team 1","IND");
        p1.setProperty("Team 2","CAM");
        currentMatchData.put(1,p1);

        Properties p2 = new Properties();
        p2.setProperty("Match Name","CC vs SS");
        p2.setProperty("Team 1","CC");
        p2.setProperty("Team 2","SS");
        currentMatchData.put(2,p2);
    }

    @GetMapping("/{id}")
    public Properties getById(@PathVariable(value = "id") int matchId){
        Properties matchData = currentMatchData.get(matchId);
        if (matchData == null) throw new IllegalArgumentException("not FOUND "+ matchId);
        return matchData;
    }
}

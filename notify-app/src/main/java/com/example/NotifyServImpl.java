package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class NotifyServImpl implements NotifyService{
    private static final Logger LOG = LoggerFactory.getLogger(NotifyServImpl.class);

    private static final String CRICKET_SERVICE_ID = "CRICKET-APP";
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    @HystrixCommand(fallbackMethod = "getMatchDetailByIdFallback")
    public Properties getMatchDetailById(int id) {
        LOG.info("Call underlying Service");
        List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(CRICKET_SERVICE_ID);
        if (serviceInstances.isEmpty()) {
            LOG.error("No service: {} , CRICKET_SERVICE_ID");
            throw new RuntimeException("not found service");
        }

        ServiceInstance cricketInstance = serviceInstances.get(0);
        String url = cricketInstance.getUri().toString()+"/"+id;
        ResponseEntity<Properties> response = restTemplate.exchange(url, HttpMethod.GET, null, Properties.class);
        Properties matchDetail = response.getBody();
        matchDetail.setProperty("source", url);
        LOG.info("Found match:{} for matchId:{} from underlying cricket service", matchDetail, id);
        return matchDetail;
    }

    public Properties getMatchDetailByIdFallback(int matchId) {
        LOG.warn("HYSTRIX FALLBACK METHOD IS INVOKED");
        Properties properties = new Properties();
        properties.setProperty("source", "getMatchDetailByIdFallback");
        properties.setProperty("matchDetail", "NOT FOUND");
        return properties;
    }
}

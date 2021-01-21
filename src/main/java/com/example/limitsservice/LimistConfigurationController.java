package com.example.limitsservice;

import com.example.limitsservice.model.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimistConfigurationController {

    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retreiveLimitsFromConfiguration() {


        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    @GetMapping("/fault-tolereance-example")
    @HystrixCommand(fallbackMethod = "fallBackRetrieveConfiguration")
    public LimitConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitConfiguration fallBackRetrieveConfiguration() {
        return new LimitConfiguration(9, 999);
    }
}

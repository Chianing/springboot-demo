package com.demo.chianing.system.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemProperties {

    @Value("${active.env}")
    private String activeEnv;

    public String getActiveEnv() {
        return activeEnv;
    }

}

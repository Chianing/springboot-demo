package com.demo.chianing.system.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 变量
 */
@Component
public class Properties {

    public static SystemProperties systemProperties;

    @Autowired
    private void setSystem(SystemProperties systemProperties) {
        Properties.systemProperties = systemProperties;
    }


}

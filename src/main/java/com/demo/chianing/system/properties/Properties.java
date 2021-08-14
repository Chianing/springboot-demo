package com.demo.chianing.system.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 全局属性
 */
@Component
public class Properties {

    /**
     * 系统属性
     */
    public static SystemProperties system;

    @Autowired
    private void setSystem(SystemProperties systemProperties) {
        Properties.system = systemProperties;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "system")
    public static class SystemProperties {
        /**
         * 系统管理员
         */
        private List<String> admins;
        /**
         * 当前激活的环境
         */
        private String activeEnv;
    }

}

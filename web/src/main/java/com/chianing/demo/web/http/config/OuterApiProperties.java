package com.chianing.demo.web.http.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 外部api属性
 *
 * @author chianing
 * @description 外部api属性
 * @date 2023/10/20 16:29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "outer-api")
public class OuterApiProperties {
    private String testHost;
}

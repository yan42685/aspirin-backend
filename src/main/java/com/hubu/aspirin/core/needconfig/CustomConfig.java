package com.hubu.aspirin.core.needconfig;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {
    public static boolean isDebug = false;
}

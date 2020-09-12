package com.hubu.aspirin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @author alex
 */
@SpringBootConfiguration
@MapperScan(basePackages = "com.hubu.aspirin.mapper")
public class MybatisConfig {
}

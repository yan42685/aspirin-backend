package com.hubu.aspirin.core.needconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @author alex
 */
@SpringBootConfiguration
@MapperScan(basePackages = "com.hubu.aspirin.mapper")
public class MybatisConfig {
}

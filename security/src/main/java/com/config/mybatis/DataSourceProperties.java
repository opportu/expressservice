package com.config.mybatis;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "datasource")
@Data
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driveClassName;
    private ClassLoader classLoader;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private String dbType;

}

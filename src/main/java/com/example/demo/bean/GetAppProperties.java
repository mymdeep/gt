package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAppProperties {
    /*获取配置文件application.properties中的端口号port*/
    @Bean
    public String port(@Value("${server.port}") String port) {
        return port;
    }

    /*获取配置文件application.properties中的上下文路径path*/
    @Bean
    public String path(@Value("${server.servlet.context-path}") String path) {
        return path;
    }
}

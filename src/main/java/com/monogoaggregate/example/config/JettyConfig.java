package com.monogoaggregate.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JettyConfig {

    @Autowired
    private SystemConfigProperties systemConfigProperties;

    /**
     * Configuration for servlet container.
     *
     * @return configured servlet container
     */
    @Bean
    public ServletWebServerFactory servletContainer() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        int port = Integer.parseInt(systemConfigProperties.getServerPort());
        factory.setPort(port);
        return factory;
    }
}


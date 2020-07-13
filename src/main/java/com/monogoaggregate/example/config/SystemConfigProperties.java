package com.monogoaggregate.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigProperties {

    @Value("${server.name}")
    private String serverName;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.profiles.active}")
    private String activeSpringProfile;

    @Value("${server.port}")
    private String serverPort;

    public String getServerName() {
        return serverName;
    }

    public String getMongoUri() {
        return mongoUri;
    }

    public String getActiveSpringProfile() {
        return activeSpringProfile;
    }

    public String getServerPort() {
        return serverPort;
    }
}

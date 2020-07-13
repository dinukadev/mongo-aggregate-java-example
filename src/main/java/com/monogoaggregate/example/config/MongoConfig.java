package com.monogoaggregate.example.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by tudor on 1/05/17.
 */
public class MongoConfig {

    @Configuration
    @EnableMongoRepositories(basePackages = {"com.monogoaggregate"})
    @EnableTransactionManagement
    @Profile({"dev", "test"})
    public static class MongoConfigDev extends AbstractMongoClientConfiguration {

        @Autowired
        private SystemConfigProperties systemConfigProperties;

        @Override
        protected String getDatabaseName() {
            String serviceName = systemConfigProperties.getServerName();
            String environmentName = systemConfigProperties.getActiveSpringProfile();
            return serviceName + "-" + environmentName;
        }

        @Override
        public MongoClient mongoClient() {
            return MongoClients.create("mongodb://" + systemConfigProperties.getMongoUri());
        }
    }

}

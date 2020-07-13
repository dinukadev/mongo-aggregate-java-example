package com.monogoaggregate.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = "com.monogoaggregate")
public class Application {

    public static void main(String[] args) throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String logFileName = "mongo-aggregate-example";
        System.setProperty("log.file.name", logFileName + "-" + hostAddress);
        SpringApplication.run(Application.class, args);
    }

}

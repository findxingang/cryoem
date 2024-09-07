package com.example.cryoem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class CryoemApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CryoemApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String appName = environment.getProperty("spring.application.name") != null ? environment.getProperty("spring.application.name") : "";
        String port = environment.getProperty("server.port") != null ? environment.getProperty("server.port") : "8080";
        String path = environment.getProperty("server.servlet.context-path") != null ? environment.getProperty("server.servlet.context-path") : "";
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(
                "\n\n\t" +
                        "----------------------------------------------------------\n\t" +
                        "Application " + appName +" is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                        "External: \thttp://" + ip + ":" + port + path + "/\n\n\t" +
                        "------------------------------------------------------------"
        );
    }

}

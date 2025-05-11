package org.example.ragservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class RagServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RagServiceApplication.class, args);
        Environment env = context.getEnvironment();
        System.out.println("Активный профиль: " + Arrays.toString(env.getActiveProfiles()));
        System.out.println("Порт сервера: " + env.getProperty("server.port"));
    }

}

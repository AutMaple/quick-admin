package com.autmaple.amqp;

import com.autmaple.amqp.components.DataSender;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableRabbit
@EnableConfigurationProperties
public class AmqpApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AmqpApplication.class, args);
        DataSender dataSender = context.getBean("dataSender", DataSender.class);
        dataSender.send();
    }
}

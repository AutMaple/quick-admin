package com.autmaple.amqp.components;

import com.autmaple.amqp.configs.RabbitMqProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSender {

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties rabbitMqProperties;

    public void send() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("==========> {}", "Hello World");
            rabbitTemplate.convertAndSend(
                    rabbitMqProperties.getExchange(),
                    rabbitMqProperties.getRawQueueKey(),
                    "Hello, World");
        }, 0, 1, TimeUnit.SECONDS);
    }

    @RabbitListener(queues = "${rabbitmq.rawQueueName}")
    public void consume(String message) {
        log.info("<===== {}", message);
    }
}

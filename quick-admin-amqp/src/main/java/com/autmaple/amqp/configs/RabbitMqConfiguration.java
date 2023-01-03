package com.autmaple.amqp.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfiguration {

    private final RabbitMqProperties rabbitMqProperties;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMqProperties.getHost());
        connectionFactory.setPort(rabbitMqProperties.getPort());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
        connectionFactory.setChannelCacheSize(5);
        return connectionFactory;
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(messageConverter());
        // 没有指定路由键时，使用该方法设置的路由键
        // template.setRoutingKey(resolvedQueue);
        // 没有指定目标队列时，将该方法设置的队列作为目标队列
        // template.setDefaultReceiveQueue(resolvedQueue);
        return template;
    }

    @Bean
    Queue rawQueue() {
        return new Queue(rabbitMqProperties.getRawQueueName(), true, false, false);
    }

    @Bean
    Queue resolvedQueue() {
        return new Queue(rabbitMqProperties.getResolvedQueueName(), true, false, false);
    }

    @Bean
    AmqpAdmin rabbitAdmin() {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        // declareXXXX 会在 rabbitMQ 中创建对应的结构
        admin.declareExchange(directExchange());
        admin.declareQueue(rawQueue());
        admin.declareQueue(resolvedQueue());
        admin.declareBinding(rawQueueBinding());
        admin.declareBinding(resolvedQueueBinding());
        return admin;
    }

    @Bean
    Binding rawQueueBinding() {
        return new Binding(
                rabbitMqProperties.getRawQueueName(),
                Binding.DestinationType.QUEUE,
                rabbitMqProperties.getExchange(),
                rabbitMqProperties.getRawQueueKey(),
                null
        );
    }

    @Bean
    Binding resolvedQueueBinding() {
        return new Binding(
                rabbitMqProperties.getResolvedQueueName(),
                Binding.DestinationType.QUEUE,
                rabbitMqProperties.getExchange(),
                rabbitMqProperties.getResolvedQueueKey(),
                null
        );
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(rabbitMqProperties.getExchange(), true, false);
    }

    @Bean
    RabbitListenerContainerFactory<?> rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

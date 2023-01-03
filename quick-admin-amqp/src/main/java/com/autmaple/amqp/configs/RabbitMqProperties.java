package com.autmaple.amqp.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitMqProperties {
    /**
     * 用户名
     */
    private String username = "guest";

    /**
     * 密码
     */
    private String password = "guest";

    /**
     * 存放原始数据的队列
     */
    private String rawQueueName = "";

    /**
     * 存放已解析数据的队列
     */
    private String resolvedQueueName = "";

    /**
     * 交换机名
     */
    private String exchange = "";

    /**
     * 原始数据队列路由键
     */
    private String rawQueueKey = "";

    /**
     * 已解析数据队列路由键
     */
    private String resolvedQueueKey = "";

    /**
     * 虚拟主机或者说是仓库名
     */
    private String virtualHost = "";

    /**
     * rabbitmq 主机名
     */
    private String host = "localhost";

    /**
     * rabbitmq 的端口号
     */
    private Integer port = 5672;
}

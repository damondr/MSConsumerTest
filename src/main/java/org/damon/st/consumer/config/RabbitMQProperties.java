package org.damon.st.consumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Setter
@Getter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.rabbitmq")
public class RabbitMQProperties {
    private String queueName;
}
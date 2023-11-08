package org.damon.st.consumer.config;

import org.damon.st.consumer.dto.UserOperationDto;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.exchangeName}")
    private String exchangeName;
    @Bean
    public RabbitMQProperties userInfoRabbitMQProperties() {
        return new RabbitMQProperties();
    }

    @Bean
    public Exchange userInfoExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {

        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(DefaultJackson2JavaTypeMapper.TypePrecedence.INFERRED);
        typeMapper.setTrustedPackages("*");
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put(
                "org.damon.st.producer.dto.UserOperationDto", UserOperationDto.class);
        typeMapper.setIdClassMapping(idClassMapping);
        messageConverter.setClassMapper(typeMapper);

        return messageConverter;
    }
}
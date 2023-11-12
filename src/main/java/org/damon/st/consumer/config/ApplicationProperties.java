package org.damon.st.consumer.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    @Valid
    private RabbitQueuesProperties rabbit;

    @Data
    public static class RabbitQueuesProperties {
        @NotBlank
        private String exchangeName;

        @NotBlank
        private String queueName;
    }
}

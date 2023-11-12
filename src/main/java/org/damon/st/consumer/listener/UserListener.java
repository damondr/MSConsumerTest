package org.damon.st.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.damon.st.consumer.config.ApplicationProperties;
import org.damon.st.consumer.dto.UserOperationDto;
import org.damon.st.consumer.exception.UsersException;
import org.damon.st.consumer.mapstruct.UserMapper;
import org.damon.st.consumer.model.User;
import org.damon.st.consumer.service.UsersService;
import org.damon.st.consumer.utils.UserOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserListener {
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;
    private final UsersService usersService;
    private final ApplicationProperties applicationProperties;

    @RabbitListener(queues = "#{@applicationProperties.getRabbit().getQueueName()}")
    public void listenQueue(@Payload Message message) {
        try {
            UserOperationDto userOperationDto = objectMapper.readValue(message.getBody(), UserOperationDto.class);
            usersService.processUserOperation(
                    userMapper.toEntity(userOperationDto.getUser()),
                    UserOperation.valueOf(userOperationDto.getOperation().toUpperCase())
            );
        } catch (Exception e) {
            throw new UsersException(e.getMessage());
        }
    }
}

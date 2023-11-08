package org.damon.st.consumer.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.damon.st.consumer.dto.UserDto;
import org.damon.st.consumer.dto.UserOperationDto;
import org.damon.st.consumer.exception.UsersException;
import org.damon.st.consumer.mapstruct.UserMapper;
import org.damon.st.consumer.model.User;
import org.damon.st.consumer.service.UsersService;
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

    @RabbitListener(queues = "#{@userInfoRabbitMQProperties.queueName}")
    public void listenQueue(@Payload Message message) {
        try {
            UserOperationDto userOperationDto = objectMapper.readValue(message.getBody(), UserOperationDto.class);
            UserDto userDto = userOperationDto.getUser();
            User user = userMapper.toEntity(userDto);
            String operation = userOperationDto.getOperation();
            if ("create".equals(operation)) {
                usersService.createUser(user);
            } else if ("update".equals(operation)) {
                usersService.updateUser(user);
            } else if ("delete".equals(operation)) {
                usersService.deleteUser(user);
            }
        } catch (Exception e) {
            throw new UsersException(e.getMessage());
        }
    }
}

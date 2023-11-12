package org.damon.st.consumer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserOperationDto {
    private String operation;
    private UserDto user;
}
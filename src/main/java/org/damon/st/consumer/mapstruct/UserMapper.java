package org.damon.st.consumer.mapstruct;

import org.damon.st.consumer.dto.UserDto;
import org.damon.st.consumer.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}

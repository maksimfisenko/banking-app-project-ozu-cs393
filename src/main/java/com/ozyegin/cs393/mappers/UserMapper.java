package com.ozyegin.cs393.mappers;

import com.ozyegin.cs393.dto.UserDTO;
import com.ozyegin.cs393.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({})
    UserDTO userToUserDto(User user);

    @Mappings({})
    User userDtoToUser(UserDTO userDto);

    default List<UserDTO> UsersToUserDtos (List<User> users){
        return users.stream().map(this::userToUserDto).collect(Collectors.toList());
    }
}

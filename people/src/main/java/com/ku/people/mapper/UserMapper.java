package com.ku.people.mapper;

import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setSurname(user.getSurname())
                .setName(user.getName())
                .setRoles(RoleMapper.toListDto(user.getRoles()))
                .setDetails(DetailMapper.toListDto(user.getDetails()));
    }

    public static List<UserListDto> toListDto(List<User> users) {
        return users.stream()
                .map(UserMapper::toListDto)
                .collect(Collectors.toList());
    }

    public static Set<UserListDto> toListDto(Set<User> users) {
        return users.stream()
                .map(UserMapper::toListDto)
                .collect(Collectors.toSet());
    }

    public static UserListDto toListDto(User user) {
        return new UserListDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setSurname(user.getSurname())
                .setName(user.getName());
    }

    public static User fromSaveDto(UserSaveDto userSaveDto) {
        return new User()
                .setName(userSaveDto.getName())
                .setSurname(userSaveDto.getSurname())
                .setUsername(userSaveDto.getUsername())
                .setPassword(userSaveDto.getPassword());
    }

    public static User fromSaveDtoForUpdate(UserSaveDto userSaveDto) {
        return new User()
                .setId(userSaveDto.getId())
                .setName(userSaveDto.getName())
                .setSurname(userSaveDto.getSurname())
                .setUsername(userSaveDto.getUsername())
                .setPassword(userSaveDto.getPassword());
    }
}

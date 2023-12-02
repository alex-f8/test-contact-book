package com.example.testcontactbook.models.mappers;

import com.example.testcontactbook.models.dtos.UserDTO;
import com.example.testcontactbook.models.entities.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullName(user.getFullName());
        userDTO.setActive(user.isActive());
        return userDTO;
    }

    public static User fromDTO(UserDTO userDTO) {
        if (userDTO == null) return null;
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setActive(userDTO.isActive());
        return user;
    }
}

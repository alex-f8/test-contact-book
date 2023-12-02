package com.example.testcontactbook.services;

import com.example.testcontactbook.models.dtos.UserDTO;

public interface UserService {

    Long getUserIdByUsername(String username);

    UserDTO create(UserDTO userDTO);
}

package com.example.testcontactbook.models.dtos;

import com.example.testcontactbook.models.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserDTO {
    private Long id;
    private String username;
    @EqualsAndHashCode.Exclude
    private String password;
    private String fullName;
    private boolean active;
}

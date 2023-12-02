package com.example.testcontactbook.models.dtos;

import lombok.Data;

@Data
public class ContactDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
}

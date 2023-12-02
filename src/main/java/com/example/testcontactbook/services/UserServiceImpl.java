package com.example.testcontactbook.services;

import com.example.testcontactbook.models.dtos.UserDTO;
import com.example.testcontactbook.repositories.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Long getUserIdByUsername(String username) {
        return userRepository.findIdByUsername(username).orElseThrow(
                () -> new ValidationException("Unexpected error: user with username='%s' not found".formatted(username))
        );
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        return null;
    }
}

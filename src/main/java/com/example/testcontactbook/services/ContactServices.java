package com.example.testcontactbook.services;

import com.example.testcontactbook.models.dtos.ContactDTO;
import org.springframework.data.domain.Page;

public interface ContactServices {

    ContactDTO getById(Long id, Long userId);

    Page<ContactDTO> getAll(Integer page, Integer size);

    Page<ContactDTO> getMyAll(Long userId, Integer page, Integer size);

    ContactDTO create(Long userId, ContactDTO obj);

    ContactDTO update(Long id, Long userId, ContactDTO obj);

    void deleteById(Long id, Long userId);
}

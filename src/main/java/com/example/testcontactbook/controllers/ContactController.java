package com.example.testcontactbook.controllers;

import com.example.testcontactbook.models.dtos.ContactDTO;
import com.example.testcontactbook.services.ContactServices;
import com.example.testcontactbook.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactServices contactServices;
    private final UserService userService;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO getById(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.getUserIdByUsername(authentication.getName());
        return contactServices.getById(id, userId);
    }

    /**
     * Only for admins
     */
    @GetMapping(value = "get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<ContactDTO> getAll4Admin(Authentication authentication, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        return contactServices.getAll(page, size);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<ContactDTO> getAll(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size, Authentication authentication) {
        Long userId = userService.getUserIdByUsername(authentication.getName());
        return contactServices.getMyAll(userId, page, size);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactDTO obj, Authentication authentication) {
        Long userId = userService.getUserIdByUsername(authentication.getName());
        return contactServices.create(userId, obj);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ContactDTO update(@PathVariable Long id, @RequestBody ContactDTO obj, Authentication authentication) {
        Long userId = userService.getUserIdByUsername(authentication.getName());
        return contactServices.update(id, userId, obj);
    }

    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable Long id, Authentication authentication) {
        Long userId = userService.getUserIdByUsername(authentication.getName());
        contactServices.deleteById(id, userId);
    }
}

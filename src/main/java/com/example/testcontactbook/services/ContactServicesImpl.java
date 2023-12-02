package com.example.testcontactbook.services;

import com.example.testcontactbook.models.dtos.ContactDTO;
import com.example.testcontactbook.models.entities.Contact;
import com.example.testcontactbook.models.mappers.ContactMapper;
import com.example.testcontactbook.repositories.ContactRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.example.testcontactbook.models.mappers.ContactMapper.fromDTO;
import static com.example.testcontactbook.models.mappers.ContactMapper.toDTO;

@Service
@RequiredArgsConstructor
public class ContactServicesImpl implements ContactServices {
    private final ContactRepository contactRepository;

    @Transactional
    public Contact getEntityById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ValidationException("contact not found"));
    }

    @Override
    public ContactDTO getById(Long id, Long userId) {
        Contact contact = getEntityById(id);
        if (!contact.getUserId().equals(userId)) {
            throw new ValidationException("Contact doesn't belong user");
        }
        return toDTO(contact);
    }

    @Override
    public Page<ContactDTO> getAll(Integer page, Integer size) {
        return contactRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending())).map(ContactMapper::toDTO);
    }

    @Override
    public Page<ContactDTO> getMyAll(Long userId, Integer page, Integer size) {
        return contactRepository.findAllByUserId(userId, PageRequest.of(page, size, Sort.by("id").descending())).map(ContactMapper::toDTO);
    }

    @Override
    public ContactDTO create(Long userId, ContactDTO obj) {
        Contact contact = fromDTO(obj);
        contact.setUserId(userId);
        Contact savedContact = contactRepository.save(contact);
        return toDTO(savedContact);
    }

    @Transactional
    @Override
    public ContactDTO update(Long id, Long userId, ContactDTO obj) {
        Contact contact = getEntityById(id);
        if (!contact.getUserId().equals(userId)) {
            throw new ValidationException("Contact doesn't belong user");
        }
        contact.setFirstName(obj.getFirstName());
        contact.setLastName(obj.getLastName());
        contact.setPhone(obj.getPhone());
        contact.setEmail(obj.getEmail());
        contact.setAddress(obj.getAddress());

        return toDTO(contact);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        Contact contact = contactRepository.getReferenceById(id);
        if (!contact.getUserId().equals(userId)) {
            throw new ValidationException("Contact doesn't belong user");
        }
        contactRepository.deleteById(id);
    }
}

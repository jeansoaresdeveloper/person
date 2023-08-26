package com.api.contact;

import com.api.contact.dto.ContactCreateDto;
import com.api.contact.dto.ContactDto;
import com.api.person.Person;
import com.api.person.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    public Page<ContactDto> listContact(Pageable pagination) {
        return repository.findAll(pagination).map(ContactDto::new);
    }

    public ContactDto listContactById(Long id) {
        Contact contact = repository.getReferenceById(id);
        return new ContactDto(contact);
    }

    public Contact createContact(ContactCreateDto data) {
        Contact contact = new Contact(data);
        repository.save(contact);
        return contact;
    }

    public Contact getContactForUpdate(Long id) {
        return repository.getReferenceById(id);
    }
}

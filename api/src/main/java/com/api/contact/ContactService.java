package com.api.contact;

import com.api.contact.dto.ContactCreateDto;
import com.api.contact.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Contact createContact(Long personId, ContactCreateDto data) {
        Contact contact = new Contact(personId, data);
        repository.save(contact);
        return contact;
    }

    public Page<ContactDto> getContactByPerson(Pageable pagination, Long personId) {
        return repository.findByPersonId(pagination, personId);
    }

    public Contact getContactForUpdate(Long id) {
        return repository.getReferenceById(id);
    }

    public void deleteContactByPersonId(Long personId) {
        repository.deleteAllByPersonId(personId);
    }
}

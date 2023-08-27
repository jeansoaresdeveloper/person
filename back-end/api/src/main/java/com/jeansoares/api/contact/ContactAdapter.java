package com.jeansoares.api.contact;

import com.jeansoares.api.adapter.Adapter;
import com.jeansoares.api.contact.dto.ContactDto;
import org.springframework.stereotype.Service;

@Service
public class ContactAdapter implements Adapter<ContactDto, Contact> {

    @Override
    public ContactDto fromEntity(Contact contact) {
        return new ContactDto(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail());
    }

    @Override
    public Contact fromDto(ContactDto contactDto) {
        return new Contact(contactDto.getName(), contactDto.getEmail(), contactDto.getPhone());
    }

}

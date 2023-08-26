package com.api.contact.dto;

import com.api.contact.Contact;

public record ContactDto(
        Long id,
        Long person_id,
        String name,
        String phone,
        String email
) {
    public ContactDto(Contact contact) {
        this(contact.getId(), contact.getPerson_id(), contact.getName(), contact.getPhone(), contact.getPhone());
    }
}

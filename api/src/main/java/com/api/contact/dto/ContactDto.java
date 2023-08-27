package com.api.contact.dto;

import com.api.contact.Contact;

public record ContactDto(
        Long id,
        Long personId,
        String name,
        String phone,
        String email
) {
    public ContactDto(Contact contact) {
        this(contact.getId(), contact.getPersonId(), contact.getName(), contact.getPhone(), contact.getPhone());
    }
}

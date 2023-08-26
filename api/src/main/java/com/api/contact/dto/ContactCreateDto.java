package com.api.contact.dto;

import com.api.contact.Contact;

public record ContactCreateDto(
        Long person_id,
        String name,
        String phone,
        String email
) {}

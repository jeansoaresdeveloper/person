package com.api.person.dto;

import com.api.contact.dto.ContactCreateDto;
import com.api.person.Person;

import java.time.LocalDate;

public record PersonCreateDto(
        String name,
        String cpf,
        LocalDate dateBirth,
        ContactCreateDto contact
) {}

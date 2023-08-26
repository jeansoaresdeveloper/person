package com.api.person.dto;

import com.api.contact.Contact;
import com.api.person.Person;

import java.time.LocalDate;
import java.util.List;

public record PersonDto(
   Long id,
   String name,
   String cpf,
   LocalDate dateBirth,

   List<Contact> contacts

) {
    public PersonDto(Person person) {
        this(person.getId(), person.getName(), person.getCpf(), person.getDateBirth(), person.getContacts());
    }
}

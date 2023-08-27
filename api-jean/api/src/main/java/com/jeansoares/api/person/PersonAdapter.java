package com.jeansoares.api.person;

import com.jeansoares.api.adapter.Adapter;
import com.jeansoares.api.contact.Contact;
import com.jeansoares.api.contact.ContactAdapter;
import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonAdapter implements Adapter<PersonDto, Person> {

    @Autowired
    private ContactAdapter adapter;

    @Override
    public PersonDto fromEntity(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getCpf(), person.getDateBirth(), getContacts(person));
    }

    private List<ContactDto> getContacts(Person person) {
        return person.getContacts().stream().map(this::getContactDto).toList();
    }

    private ContactDto getContactDto(Contact contact) {
        return adapter.fromEntity(contact);
    }

    @Override
    public Person fromDto(PersonDto personDto) {
        return new Person(personDto.getName(), personDto.getCpf(), personDto.getDateBirth());
    }
}

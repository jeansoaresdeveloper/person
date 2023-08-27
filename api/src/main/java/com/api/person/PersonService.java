package com.api.person;

import com.api.contact.Contact;
import com.api.contact.ContactRepository;
import com.api.contact.ContactService;
import com.api.person.dto.PersonCreateDto;
import com.api.person.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactService contactService;

    public Page<PersonDto> listPerson(Pageable pagination) {
        return personRepository.findAll(pagination).map(PersonDto::new);
    }

    public PersonDto listPersonById(Long id) {
        Person person = personRepository.getReferenceById(id);
        return new PersonDto(person);
    }

    public Person createPerson(PersonCreateDto data) {
        Person person = new Person(data);
        personRepository.save(person);
        contactService.createContact(person.getId(), data.contact());
        return person;
    }

    public Person getPersonByUpdate(Long id) {
        return personRepository.getReferenceById(id);
    }

    public void deletePerson(Long personId) {
        Person person = personRepository.getReferenceById(personId);
        personRepository.delete(person);
    }
}

package com.jeansoares.api.person;

import com.jeansoares.api.contact.Contact;
import com.jeansoares.api.contact.ContactAdapter;
import com.jeansoares.api.contact.ContactService;
import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.dto.PersonDto;
import com.jeansoares.api.person.dto.PersonUpdateDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PersonAdapter personAdapter;

    @Autowired
    private ContactAdapter contactAdapter;

    @Transactional
    public PersonDto register(PersonDto data) {
        Person person = personAdapter.fromDto(data);

        checkCpf(data.getCpf(), person);

        List<Contact> contacts = new ArrayList<>();
        for (ContactDto contactDto : data.getContacts()) {
            contacts.add(contactService.create(contactDto, person));
        }

        person.setContacts(contacts);
        personRepository.save(person);

        return personAdapter.fromEntity(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public Page<PersonDto> getAll(String name, Pageable pageable) {
        if (name == null) {
            return personRepository.findAll(pageable).map(this::getPersonDto);
        }

        return personRepository.findByNameContaining(name, pageable).map(this::getPersonDto);
    }

    @Transactional
    public PersonDto update(PersonUpdateDto data, Long id) {
        Person person = findById(id);

        checkCpf(data.getCpf(), person);
        person.setName(data.getName());
        person.setCpf(data.getCpf());
        person.setDateBirth(data.getDateBirth());

        return personAdapter.fromEntity(personRepository.save(person));
    }

    private void checkCpf(String cpfInput, Person personToBeSaved) {
        boolean personExists = personRepository.findByCpf(cpfInput)
                .stream()
                .anyMatch(personFound -> !Objects.equals(personFound , personToBeSaved));
        if (personExists) {
            throw new EntityExistsException("Já existe uma pessoa cadastrada com o CPF informado.");
        }
    }

    public PersonDto getById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        return personAdapter.fromEntity(person);
    }

    private PersonDto getPersonDto(Person person) {
        return personAdapter.fromEntity(person);
    }

    public List<ContactDto> getContacts(Long id) {
        return findById(id).getContacts().stream().map(contact -> contactAdapter.fromEntity(contact)).toList();
    }

    public PersonDto addContact(ContactDto data, Long id) {
        Person person = findById(id);
        contactService.create(data, person);
        return personAdapter.fromEntity(person);
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

}

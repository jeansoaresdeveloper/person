package com.api.person;

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
    private PersonRepository repository;

    public Page<PersonDto> listPerson(Pageable pagination) {
        return repository.findAll(pagination).map(PersonDto::new);
    }

    public PersonDto listPersonById(Long id) {
        Person person = repository.getReferenceById(id);
        return new PersonDto(person);
    }

    public Person createPerson(PersonCreateDto data) {
        Person person = new Person(data);
        repository.save(person);
        return person;
    }

    public Person getPersonByUpdate(Long id) {
        return repository.getReferenceById(id);
    }
}

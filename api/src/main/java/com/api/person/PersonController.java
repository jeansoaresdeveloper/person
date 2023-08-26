package com.api.person;

import com.api.person.dto.PersonCreateDto;
import com.api.person.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping()
    public ResponseEntity<Page<PersonDto>> person(Pageable pageable) {
        Page<PersonDto> page = service.listPerson(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PersonDto> personById(@PathVariable Long id) {
        PersonDto person = service.listPersonById(id);
        return ResponseEntity.ok(person);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonCreateDto data) {
        Person person = service.createPerson(data);
        return ResponseEntity.ok(person);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto data, @PathVariable Long id) {
        Person person = service.getPersonByUpdate(id);
        person.updateInfo(data);
        return ResponseEntity.ok(new PersonDto(person));
    }
}

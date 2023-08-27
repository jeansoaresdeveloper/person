package com.api.person;

import com.api.contact.Contact;
import com.api.contact.ContactService;
import com.api.contact.dto.ContactCreateDto;
import com.api.contact.dto.ContactDto;
import com.api.person.dto.PersonCreateDto;
import com.api.person.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactService contactService;

    @GetMapping()
    public ResponseEntity<Page<PersonDto>> person(Pageable pageable) {
        Page<PersonDto> page = personService.listPerson(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PersonDto> personById(@PathVariable Long id) {
        PersonDto person = personService.listPersonById(id);
        return ResponseEntity.ok(person);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonCreateDto data) {
        Person person = personService.createPerson(data);
        return ResponseEntity.ok(person);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto data, @PathVariable Long id) {
        Person person = personService.getPersonByUpdate(id);
        person.updateInfo(data);
        return ResponseEntity.ok(new PersonDto(person));
    }

    @Transactional
    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.deletePerson(personId);
    }

    @GetMapping("/{id}/contact")
    public ResponseEntity<Page<ContactDto>> getContactPerson(Pageable pagination, @PathVariable Long id) {
        Page<ContactDto> contact = contactService.getContactByPerson(pagination, id);
        return ResponseEntity.ok(contact);
    }

    @Transactional
    @PostMapping("/{personId}/contact")
    public ResponseEntity<Contact> createContact(@PathVariable Long personId, @RequestBody @Valid ContactCreateDto data) {
        Contact contact = contactService.createContact(personId, data);
        return ResponseEntity.ok(contact);
    }

}

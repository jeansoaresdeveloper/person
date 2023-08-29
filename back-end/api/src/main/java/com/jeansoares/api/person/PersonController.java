package com.jeansoares.api.person;

import com.jeansoares.api.contact.ContactService;
import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.dto.PersonDto;
import com.jeansoares.api.person.dto.PersonUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<Page<PersonDto>> getAll(
            @RequestParam(required = false) String name,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok().body(personService.getAll(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(personService.getById(id));
    }

    @PostMapping
    public PersonDto create(@RequestBody @Valid PersonDto data) {
        return personService.register(data);
    }

    @PostMapping("/{id}/contact")
    public ResponseEntity<PersonDto> addContact(@RequestBody @Valid ContactDto data, @PathVariable Long id) {
        return ResponseEntity.ok().body(personService.addContact(data, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody @Valid PersonUpdateDto data) {
        PersonDto personDto = personService.update(data, id);
        return ResponseEntity.ok().body(personDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        personService.deleteById(id);
    }

    @GetMapping("/{id}/contact")
    public ResponseEntity<List<ContactDto>> getContacts(@PathVariable Long id) {
        List<ContactDto> contacts = personService.getContacts(id);
        return ResponseEntity.ok().body(contacts);
    }

}

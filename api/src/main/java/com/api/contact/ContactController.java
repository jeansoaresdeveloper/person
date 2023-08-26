package com.api.contact;

import com.api.contact.dto.ContactCreateDto;
import com.api.contact.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService service;
    @GetMapping()
    public ResponseEntity<Page<ContactDto>> contact(Pageable pageable) {
        Page<ContactDto> page = service.listContact(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ContactDto> contactById(@PathVariable Long id) {
        ContactDto contact = service.listContactById(id);
        return ResponseEntity.ok(contact);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactCreateDto data) {
        Contact contact = service.createContact(data);
        return ResponseEntity.ok(contact);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto data, @PathVariable Long id) {
        Contact contact = service.getContactForUpdate(id);
        contact.updateInfo(data);
        return ResponseEntity.ok(new ContactDto(contact));
    }
}

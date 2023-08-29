package com.jeansoares.api.contact;

import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.contact.dto.ContactUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping()
    public ResponseEntity<Page<ContactDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(contactService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(contactService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> update(@PathVariable Long id, @RequestBody ContactUpdateDto data) {
        ContactDto contactDto = contactService.update(data, id);
        return ResponseEntity.ok().body(contactDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.deleteById(id);
    }

}

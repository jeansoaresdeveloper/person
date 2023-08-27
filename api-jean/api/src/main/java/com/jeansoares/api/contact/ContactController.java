package com.jeansoares.api.contact;

import com.jeansoares.api.contact.dto.ContactDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public void getAllContact(){}

    @GetMapping("/{id}")
    public void getContact(@PathVariable Long id) {}

    @PutMapping("/{id}")
    public void updateContact(@PathVariable Long id, @RequestBody ContactDto data) {}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactService.deleteById(id);
    }

}

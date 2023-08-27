package com.jeansoares.api.contact;

import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactAdapter contactAdapter;

    private ContactDto getContactDto(Contact contact) {
        return contactAdapter.fromEntity(contact);
    }

    public void deleteById(Long id) {
        Contact contact = findById(id);
        Long personId = contact.getPerson().getId();
        boolean hasOtherContact = contactRepository.existsByPersonIdAndIdNot(personId, id);

        if (!hasOtherContact) {
            throw new IllegalStateException("Pessoa deve ter ao menos um contato cadastrado");
        }

        contactRepository.deleteById(id);
    }

    private Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
    }

    public ContactDto create(ContactDto data, Person person) {
        Contact contact = contactAdapter.fromDto(data);
        contact.setPerson(person);
        contactRepository.save(contact);
        data.setId(contact.getId());
        return data;
    }

}

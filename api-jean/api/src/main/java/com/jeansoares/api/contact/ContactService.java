package com.jeansoares.api.contact;

import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactAdapter contactAdapter;

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

    public Contact create(ContactDto data, Person person) {
        Contact contact = contactAdapter.fromDto(data);
        contact.setPerson(person);
        return contactRepository.save(contact);
    }

    public Page<ContactDto> getAll(Pageable pageable) {
        return contactRepository.findAll(pageable).map(this::getContactDto);
    }

    private ContactDto getContactDto(Contact contact) {
        return contactAdapter.fromEntity(contact);
    }

    public ContactDto getById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
        return contactAdapter.fromEntity(contact);
    }
}

package com.jeansoares.api.contact;

import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.contact.dto.ContactUpdateDto;
import com.jeansoares.api.person.Person;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Transactional
    public Contact create(ContactDto data, Person person) {
        Contact contact = contactAdapter.fromDto(data);

        checkEmail(data.getEmail(), contact);
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

    @Transactional
    public ContactDto update(ContactUpdateDto data, Long id) {
        Contact contact = findById(id);

        checkEmail(data.getEmail(), contact);
        contact.setName(data.getName());
        contact.setEmail(data.getEmail());
        contact.setPhone(data.getPhone());

        return contactAdapter.fromEntity(contactRepository.save(contact));
    }

    private Contact findById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
    }

    private void checkEmail(String emailInput, Contact contactToBeSaved) {
        Boolean contactExists = contactRepository.findByEmail(emailInput)
                .stream()
                .anyMatch(contactFound -> !Objects.equals(contactFound, contactToBeSaved));
        if (contactExists) {
            throw new EntityExistsException("Já existe um contato cadastrado com o e-mail informado.");
        }

    }
}

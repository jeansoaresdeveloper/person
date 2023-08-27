package com.jeansoares.api.contact;

import com.jeansoares.api.contact.dto.ContactDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Boolean existsByPersonIdAndIdNot(Long personId, Long id);

    List<Contact> findByPersonId(Long personId);
}

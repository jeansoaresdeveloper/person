package com.jeansoares.api.contact;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Boolean existsByPersonIdAndIdNot(Long personId, Long id);

    List<Contact> findByPersonId(Long personId);

    Optional<Contact> findByEmail(String emailInput);
}

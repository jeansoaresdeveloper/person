package com.api.contact;

import com.api.contact.dto.ContactDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<ContactDto> findByPersonId(Pageable pagination, Long id);
    List<Contact> findByPersonId(Long id);
    @Query(value = "SELECT c FROM Contact c WHERE c.personId = :id")
    List<Contact> findByPersonIdLimitTwo(@Param("id") Long id);

    void deleteAllByPersonId(Long personId);

    Boolean existsByPersonIdAndIdNot(Long personId, Long id);
}

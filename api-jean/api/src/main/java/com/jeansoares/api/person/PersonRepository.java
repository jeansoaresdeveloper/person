package com.jeansoares.api.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Boolean existsByCpfAndIdNot(String cpf, Long id);
    Boolean existsByCpf(String cpf);

    Optional<Person> findByCpf(String cpf);

}

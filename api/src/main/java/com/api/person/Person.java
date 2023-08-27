package com.api.person;

import com.api.contact.Contact;
import com.api.person.dto.PersonCreateDto;
import com.api.person.dto.PersonDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateBirth;
    @OneToMany
    @JoinColumn(name = "personId")
    private List<Contact> contacts;

    public Person(PersonCreateDto data) {
        this.name = data.name();
        this.cpf = data.cpf();
        this.dateBirth = data.dateBirth();
    }

    public void updateInfo(PersonDto data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.cpf() != null) {
            this.cpf = data.cpf();
        }
        if (this.dateBirth != null) {
            this.dateBirth = data.dateBirth();
        }
    }
}

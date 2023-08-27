package com.jeansoares.api.person;

import com.jeansoares.api.contact.Contact;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    private LocalDate dateBirth;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Contact> contacts;

    public Person(String name, String cpf, LocalDate dateBirth) {
        this.name = name;
        this.cpf = cpf;
        this.dateBirth = dateBirth;
    }

}

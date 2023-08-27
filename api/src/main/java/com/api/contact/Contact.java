package com.api.contact;

import com.api.contact.dto.ContactCreateDto;
import com.api.contact.dto.ContactDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;

@Entity
@Table(name = "contact")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long personId;
    private String name;
    private String phone;
    private String email;

    public Contact(Long personId, ContactCreateDto data) {
        this.personId = personId;
        this.name = data.name();
        this.phone = data.phone();
        this.email = data.email();
    }

    public void updateInfo(ContactDto data) {
        if (this.personId != null) {
            this.personId = data.personId();
        }
        if (this.name != null) {
            this.name = data.name();
        }
        if (this.phone != null) {
            this.phone = data.phone();
        }
        if (this.email != null) {
            this.email = data.email();
        }
    }
}

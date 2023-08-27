package com.jeansoares.api.person.dto;

import com.jeansoares.api.contact.Contact;
import com.jeansoares.api.contact.dto.ContactDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PersonDto {

        @Setter
        private Long id;

        @NotBlank(message = "Insira um nome")
        private String name;

        @NotBlank(message = "Insira um cpf")
        @CPF(message = "CPF inválido")
        private String cpf;

        @NotNull(message = "Insira uma data de nascimento")
        @Past(message = "Data de nascimento não pode ser no futuro")
        LocalDate dateBirth;

        @NotEmpty(message = "Informe ao menos um contato")
        List<ContactDto> contacts;

}

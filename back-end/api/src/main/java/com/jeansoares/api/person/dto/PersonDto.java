package com.jeansoares.api.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeansoares.api.contact.dto.ContactDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class PersonDto {

        @Setter
        @JsonProperty("id")
        private Long id;

        @JsonProperty("name")
        @NotBlank(message = "Insira um nome")
        private String name;

        @JsonProperty("cpf")
        @NotBlank(message = "Insira um cpf")
        @CPF(message = "CPF inválido")
        private String cpf;

        @JsonProperty("dateBirth")
        @NotNull(message = "Insira uma data de nascimento")
        @Past(message = "Data de nascimento não pode ser no futuro")
        LocalDate dateBirth;

        @JsonProperty("contacts")
        @NotEmpty(message = "Informe ao menos um contato")
        List<ContactDto> contacts;

}

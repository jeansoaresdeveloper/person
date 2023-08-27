package com.api.person.dto;

import com.api.contact.dto.ContactCreateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PersonCreateDto(
        @NotBlank(message = "Insira um nome")
        String name,
        @NotBlank(message = "Insira um cpf")
        @CPF(message = "CPF inválido")
        String cpf,
        @NotNull(message = "Insira uma data de nascimento")
        @Past(message = "Data de nascimento não pode ser no futuro")
        LocalDate dateBirth,
        @NotNull(message = "Insira um contato")
        @Valid
        ContactCreateDto contact
) {}

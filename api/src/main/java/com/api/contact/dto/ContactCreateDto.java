package com.api.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ContactCreateDto(
        @NotBlank(message = "Insira um nome")
        String name,
        @NotBlank(message = "Insira um telefone")
        @Pattern(regexp = "^\\(\\d{2}\\)\\s?9?\\d{4}-\\d{4}$", message = "Telefone inválido")
        String phone,
        @NotBlank(message = "Insira um e-mail")
        @Email(message = "E-mail inválido")
        String email
) {}

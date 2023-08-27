package com.jeansoares.api.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateDto {

    private static final String PHONE_PATTERN_REGEX = "^\\(\\d{2}\\)\\s?9?\\d{4}-\\d{4}$";

    @NotBlank(message = "Insira um nome")
    private String name;

    @NotBlank(message = "Insira um telefone")
    @Pattern(regexp = PHONE_PATTERN_REGEX, message = "Telefone inválido")
    private String phone;

    @NotBlank(message = "Insira um e-mail")
    @Email(message = "E-mail inválido")
    private String email;

}

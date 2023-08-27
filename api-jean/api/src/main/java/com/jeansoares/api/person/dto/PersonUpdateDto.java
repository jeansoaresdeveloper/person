package com.jeansoares.api.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PersonUpdateDto {

    @NotBlank(message = "Insira um nome")
    private String name;

    @NotBlank(message = "Insira um cpf")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Insira uma data de nascimento")
    @Past(message = "Data de nascimento não pode ser no futuro")
    LocalDate dateBirth;

}

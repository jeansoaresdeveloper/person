package com.jeansoares.api.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Test
    void should_get_all_and_return_status_ok() throws Exception {
        List<PersonDto> personDtos = new ArrayList<>();
        Page<PersonDto> persons = new PageImpl<>(personDtos);

        Mockito.when(service.getAll(Mockito.any(Pageable.class))).thenReturn(persons);

        mockMvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    void should_get_by_id_and_return_status_ok() throws Exception {
            List<ContactDto> contacts = new ArrayList<>();
            contacts.add(new ContactDto());
            PersonDto person = new PersonDto(1L, "Um teste", "838.679.610-34", LocalDate.now().minusDays(5), contacts);

            Mockito.when(service.getById(Mockito.anyLong())).thenReturn(person);

            mockMvc.perform(MockMvcRequestBuilders.get("/person/3"))
                    .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        List<ContactDto> contacts = new ArrayList<>();
        contacts.add(new ContactDto("Uma empresa", "(44) 99999-9999", "teste@gmail.com"));
        PersonDto person = new PersonDto(1L, "Um teste", "838.679.610-34", LocalDate.now().minusDays(5), contacts);

        Mockito.when(service.register(person)).thenReturn(person);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.findAndRegisterModules();
        String personJson = objectMapper.writeValueAsString(person);

        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isOk());
    }

    @Test
    void addContact() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getContacts() {
    }
}
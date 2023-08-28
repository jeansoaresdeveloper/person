package com.jeansoares.api.person;

import com.jeansoares.api.contact.dto.ContactDto;
import com.jeansoares.api.person.dto.PersonDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<PersonDto> personDtoJson;

    @Autowired
    private JacksonTester<PersonDto> personExpectedDtoJson;

    @Autowired
    private JacksonTester<ContactDto> contactDtoJson;

    @MockBean
    private PersonService service;

    @Test
    @DisplayName("should return status_ok to get all person")
    void should_get_all_and_return_status_ok() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/person"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("should return status_ok to get person by id one")
    void should_get_by_id_and_return_status_ok() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/person/1"))
                    .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("should return bad_request to get person by id one")
    void should_get_by_id_and_return_status_bad_request() throws Exception {
        when(service.getById(anyLong())).thenThrow(new EntityNotFoundException());

        var response = mockMvc.perform(MockMvcRequestBuilders.get("/person/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("should return status_ok and json expected to register person")
    void should_return_status_ok_and_json_expected_to_register_person() throws Exception {
        PersonDto personDto = createPerson();
        PersonDto expectedPerson = expectedPerson();

        when(service.register(any())).thenReturn(expectedPerson);

        var response = mockMvc.perform(MockMvcRequestBuilders.post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personDtoJson.write(personDto).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(personExpectedDtoJson.write(expectedPerson).getJson());

    }

    @Test
    @DisplayName("should return status_ok and personJsonExpected when add contact to person")
    void should_return_status_ok_and_json_expected_when_add_contact_to_person() throws Exception {
        PersonDto person = expectedPerson();
        ContactDto contact = createContact();

        when(service.addContact(any(), anyLong())).thenReturn(person);

        var response = mockMvc.perform(MockMvcRequestBuilders.post("/person/1/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contactDtoJson.write(contact).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(personExpectedDtoJson.write(person).getJson());
    }

    private PersonDto createPerson() {
        List<ContactDto> contacts = new ArrayList<>();
        contacts.add(createContact());
        return new PersonDto(null, "Um teste", "838.679.610-34", LocalDate.now().minusDays(5), contacts);
    }

    private PersonDto expectedPerson() {
        List<ContactDto> contacts = new ArrayList<>();
        contacts.add(createContact());
        return new PersonDto(31L, "Um teste", "838.679.610-34", LocalDate.now().minusDays(5), contacts);
    }

    private ContactDto createContact() {
        return new ContactDto("Uma empresa", "(44) 99999-9999", "teste123@gmail.com");
    }

    private List<ContactDto> expectedContact() {
        List<ContactDto> contacts = new ArrayList<>();
        contacts.add(new ContactDto(1L, "Uma empresa", "(44) 99999-9999", "teste123@gmail.com"));
        return contacts;
    }

    @Test
    @DisplayName("should return status ok when delete by id person")
    void should_return_status_ok_when_delete_by_id() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.delete("/person/1"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("should return status ok when get contacts by person id")
    void should_return_status_ok_when_get_contacts_by_id_person() throws Exception {
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/person/1/contact"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
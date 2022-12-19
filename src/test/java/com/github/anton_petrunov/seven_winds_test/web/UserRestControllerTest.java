package com.github.anton_petrunov.seven_winds_test.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.anton_petrunov.seven_winds_test.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserRestControllerTest {

    private static final String REST_URL = "/users";

    private final User user1 = new User(1, "petrunov.ru@gmail.com", "petrunov", "Anton", "Nikolaich", "+79312211019");
    private final User user2 = new User(2, "petrunov@kirill.ru", "Petrunov", "Kirill", "Antonovich", "+79312211019");
    private final User newUserWithoutId = new User(null, "kirilll@aa.bb", "Petrov", "Kirill", "APO", "123987");

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    @Sql({"/schema.sql", "/data.sql"})
    void whenAnyNumberOfUsersThenGetAllCorrect() throws Exception {
        String expectedUsers = write(List.of(user1, user2));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUsers));
    }

    @Test
    void whenUserExistsThenGetCorrect() throws Exception {
        String expectedUser = write(user1);
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUser));
    }

    @Test
    void whenNewUserWithoutIdThenCreateCorrert() throws Exception {
        User createdUser = new User(
                3, newUserWithoutId.getEmail(), newUserWithoutId.getSurname(), newUserWithoutId.getName(),
                newUserWithoutId.getPatronymic(), newUserWithoutId.getPhone()
        );
        mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(write(newUserWithoutId)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(write(createdUser)));
    }

    @Test
    void whenNewUserHasIdThenCreateIllegalRequestDataException() throws Exception {
        User newUserWithId = new User(120, "kirill@aa.bb", "Petrov", "Kirill", "APO", "234448");
        mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(write(newUserWithId)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void whenNewUserJsonHasErrorThenCreateBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("44444"))
                .andExpect(status().isBadRequest());
    }

    private String write(List<User> users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(users);
    }

    private String write(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

}
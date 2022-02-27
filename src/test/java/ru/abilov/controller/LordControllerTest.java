package ru.abilov.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.abilov.model.Lord;
import ru.abilov.service.LordService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LordController.class)
@RunWith(SpringRunner.class)
public class LordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LordService lordService;

    @Test
    public void addLord() throws Exception {
        String body = "{ \"name\" : \"VasyaLord\"}";
        mockMvc.perform(post("/api/v1/lords").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk());
    }

    @Test
    public void getYoungestLords() throws Exception {
        mockMvc.perform(get("/api/v1/lords")).andExpect(status().isOk());
    }

    @Test
    public void getAllLordsWithoutPlanets() throws Exception {
        mockMvc.perform(get("/api/v1/lords/withoutPlanets")).andExpect(status().isOk());
    }
}

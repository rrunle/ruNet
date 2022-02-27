package ru.abilov.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.abilov.service.LordService;
import ru.abilov.service.PlanetService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanetController.class)
@RunWith(SpringRunner.class)
public class PlanetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlanetService planetService;
    @MockBean
    LordService lordService;

    @Test
    public void addPlanet() throws Exception {
        String body = "{ \"name\" : \"newPlanet\"}";
        mockMvc.perform(post("/api/v1/planet").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated());
    }

    @Test
    public void addLordToPlanet() throws Exception {
        String body = "{ \"id\" : \"1\"}";
        mockMvc.perform(post("/api/v1/planet/{id}", 6).contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk());
    }

    @Test
    public void deletePlanet() throws Exception {
        mockMvc.perform(delete("/api/v1/planet/{id}", 1)).andExpect(status().isOk());
    }
}

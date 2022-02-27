package ru.abilov.service.implementation;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.abilov.dao.PlanetRepository;
import ru.abilov.model.Lord;
import ru.abilov.model.Planet;
import ru.abilov.service.PlanetService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanetServiceImplTest {

    @Mock
    PlanetRepository planetRepository;

    private PlanetService planetServiceTest;

    @BeforeEach
    void setUp() {
        planetServiceTest = new PlanetServiceImpl(planetRepository);
    }

    @Test
    void setLord() {
        Planet planet = new Planet();
        planet.setName("Planeta2");
        planet.setLord(new Lord());
        when(planetRepository.save(any(Planet.class))).thenReturn(planet);
        planet = planetRepository.save(planet);
        Assert.assertNotNull(planet.getLord());
    }

    @Test
    void deletePlanet() {
        Planet planet = new Planet();
        planet.setName("Planetka");
        planet.setId(1L);
        when(planetRepository.findById(planet.getId())).thenReturn(Optional.of(planet));
        planetServiceTest.deletePlanet(planet.getId());
        verify(planetRepository).deleteById(planet.getId());
    }
}
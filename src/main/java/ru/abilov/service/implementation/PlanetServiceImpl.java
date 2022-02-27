package ru.abilov.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abilov.dto.PlanetDTO;
import ru.abilov.dao.PlanetRepository;
import ru.abilov.model.Lord;
import ru.abilov.model.Planet;
import ru.abilov.service.PlanetService;

import java.util.NoSuchElementException;

@Service
public class PlanetServiceImpl implements PlanetService {

    private PlanetRepository planetRepository;

    private LordServiceImpl lordService;

    public PlanetServiceImpl(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public void savePlanet(PlanetDTO planetDTO) {
        Planet planet = new Planet();
        if (planetDTO.getLord_id() != null)
            planet.setLord(lordService.findLordById(planetDTO.getLord_id()));
        planet.setName(planetDTO.getName());
        planetRepository.save(planet);
    }

    @Override
    public Planet findPlanetById(long id) {
        return planetRepository.findById(id).get();
    }

    //назначить повелителя управлять планетой
    @Override
    public void setLord(long planetId, Lord lord) {
        Planet planet = findPlanetById(planetId);
        planet.setLord(lord);
        planetRepository.save(planet);
    }

    //уничтожить планету
    @Override
    public void deletePlanet(long id) {
        planetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
        planetRepository.deleteById(id);
    }
}

package ru.abilov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.abilov.dto.PlanetDTO;
import ru.abilov.model.Lord;
import ru.abilov.model.Planet;
import ru.abilov.service.LordService;
import ru.abilov.service.PlanetService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/planet")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    //добавление планеты
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Planet> addPlanet(@RequestBody PlanetDTO planetDTO) {
        if (planetDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        planetService.savePlanet(planetDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //назначить повелителя управлять планетой
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Planet> addLordToPlanet(@PathVariable("id") Long id, @RequestBody Lord lord) {
        if (id == null || lord == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        planetService.setLord(id, lord);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //уничтожить планету
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePlanet(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        planetService.deletePlanet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

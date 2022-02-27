package ru.abilov.service;

import ru.abilov.dto.PlanetDTO;
import ru.abilov.model.Lord;
import ru.abilov.model.Planet;

public interface PlanetService {

    public void savePlanet(PlanetDTO planet);

    //назначить повелителя управлять планетой
    public void setLord(long planetId, Lord lord);

    public Planet findPlanetById(long id);

    //уничтожить планету
    public void deletePlanet(long id);
}

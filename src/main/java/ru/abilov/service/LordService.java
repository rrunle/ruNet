package ru.abilov.service;

import ru.abilov.model.Lord;

import java.util.List;

public interface LordService {

    Lord findLordById(long id);

    void saveLord(Lord lord);

    List<Lord> getAllLords();

    List<Lord> getAllLordsWithoutPlanets();

    public List<Lord> getYoungestLords();
}

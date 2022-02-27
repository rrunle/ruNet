package ru.abilov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abilov.model.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}

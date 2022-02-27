package ru.abilov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abilov.model.Lord;

@Repository
public interface LordRepository extends JpaRepository<Lord, Long> {
}

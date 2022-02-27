package ru.abilov.service.implementation;

import org.springframework.stereotype.Service;
import ru.abilov.dao.LordRepository;
import ru.abilov.model.Lord;
import ru.abilov.service.LordService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LordServiceImpl implements LordService {

    private LordRepository lordRepository;

    public LordServiceImpl(LordRepository lordRepository) {
        this.lordRepository = lordRepository;
    }

    @Override
    public Lord findLordById(long id) {
        return lordRepository.findById(id).get();
    }

    @Override
    public void saveLord(Lord lord) {
        lordRepository.save(lord);
    }

    @Override
    public List<Lord> getAllLords() {
        return lordRepository.findAll();
    }

    @Override
    public List<Lord> getAllLordsWithoutPlanets() {
        return getAllLords().stream().filter(lord -> (lord.getPlanets() == null || lord.getPlanets().size() == 0)).collect(Collectors.toList());
    }

    @Override
    public List<Lord> getYoungestLords() {
        return getAllLords().stream().sorted(Comparator.comparing(Lord::getAge)).limit(10).collect(Collectors.toList());
    }
}



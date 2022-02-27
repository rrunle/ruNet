package ru.abilov.service.implementation;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.abilov.dao.LordRepository;
import ru.abilov.model.Lord;
import ru.abilov.model.Planet;
import ru.abilov.service.LordService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LordServiceImplTest {

    @Mock
    private LordRepository lordRepository;
    private LordService lordServiceTest;

    @BeforeEach
    void setUp() {
        lordServiceTest = new LordServiceImpl(lordRepository);
    }

    @Test
    void saveLord() {
        final Lord lord = new Lord();
        lord.setName("lordLordovich");
        lord.setAge(5);
        lord.setId(1);
        lordServiceTest.saveLord(lord);

        ArgumentCaptor<Lord> argumentCaptor = ArgumentCaptor.forClass(Lord.class);

        verify(lordRepository).save(argumentCaptor.capture());

        Lord capturedLord = argumentCaptor.getValue();

        assertThat(capturedLord).isEqualTo(lord);
    }

    @Test
    void getAllLords() {
        lordServiceTest.getAllLords();
        verify(lordRepository).findAll();
    }

    @Test
    void getAllLordsWithoutPlanets() {
        List<Lord> lords = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Lord lord = new Lord();
            lord.setName("lordLordovich");
            lord.setAge(i);
            lord.setId(i);
            lords.add(lord);
        }
        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet());
        lords.get(0).setPlanets(planets);

        when(lordServiceTest.getAllLords()).thenReturn(lords);

        lords = lordServiceTest.getAllLordsWithoutPlanets();

        Assert.assertEquals(lords.size(), 3);
    }

    @Test
    void getYoungestLords() {
        List<Lord> lords = new ArrayList<>();
        for (int i = 12; i > 0; i--) {
            Lord lord = new Lord();
            lord.setName("lordLordovich");
            lord.setAge(i);
            lord.setId(i);
            lords.add(lord);
        }

        when(lordServiceTest.getAllLords()).thenReturn(lords);
        lords = lordServiceTest.getYoungestLords();

        //проверить что в списке 10 элементов
        Assert.assertEquals(10, lords.size());
        //проверить что возраст отсортировался
        Assert.assertEquals(lords.get(9).getAge(), 10);
        Assert.assertEquals(lords.get(0).getAge(), 1);
    }
}
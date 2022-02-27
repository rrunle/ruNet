package ru.abilov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.abilov.model.Lord;
import ru.abilov.service.LordService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/lords")
public class LordController {

    private LordService lordService;

    public LordController(LordService lordService) {
        this.lordService = lordService;
    }

    //добавить лорда
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Lord addLord(@RequestBody Lord lord) {
        lordService.saveLord(lord);
        return lord;
    }

    //получение самых молодых лордов
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Lord> getYoungestLords() {
        return lordService.getYoungestLords();
    }

    //получение бездельников
    @RequestMapping(value = "/withoutPlanets", method = RequestMethod.GET)
    @ResponseBody
    public List<Lord> getAllLordsWithoutPlanets() {
        return lordService.getAllLordsWithoutPlanets();
    }

}

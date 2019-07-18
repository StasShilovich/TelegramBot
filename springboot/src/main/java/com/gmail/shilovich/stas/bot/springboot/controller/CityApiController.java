package com.gmail.shilovich.stas.bot.springboot.controller;

import com.gmail.shilovich.stas.bot.service.CityService;
import com.gmail.shilovich.stas.bot.service.model.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CityApiController {

    private final CityService cityService;

    @Autowired
    public CityApiController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/city/{id}")
    public CityDTO getCityById(@PathVariable(name = "id") Long id) {
        CityDTO cityDTO = cityService.getCity(id);
        return cityDTO;
    }

    @PostMapping("/city/add")
    public ResponseEntity<String> addCity(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
        return new ResponseEntity<>(cityDTO.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/city/remove/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable(name = "id") Long id) {
        CityDTO cityDTO = cityService.getCity(id);
        cityService.removeCity(id);
        return new ResponseEntity<>(cityDTO.toString(), HttpStatus.OK);
    }

    @PostMapping("/city/update")
    public ResponseEntity<String> updateCity(
            @RequestBody CityDTO cityDTO
    ) {
        cityService.changeCity(cityDTO);
        return new ResponseEntity<>(cityDTO.toString(), HttpStatus.OK);
    }
}
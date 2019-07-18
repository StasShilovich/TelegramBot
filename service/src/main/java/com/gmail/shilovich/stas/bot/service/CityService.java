package com.gmail.shilovich.stas.bot.service;

import com.gmail.shilovich.stas.bot.service.model.CityDTO;

public interface CityService {

    CityDTO getCity(Long id);

    void addCity(CityDTO cityDTO);

    void changeCity(CityDTO newCityDTO);

    void removeCity(Long id);
}

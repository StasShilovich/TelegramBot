package com.gmail.shilovich.stas.bot.service.converter.impl;

import com.gmail.shilovich.stas.bot.repository.model.City;
import com.gmail.shilovich.stas.bot.service.converter.CityConverter;
import com.gmail.shilovich.stas.bot.service.model.CityDTO;
import org.springframework.stereotype.Component;

@Component
public class CityConverterImpl implements CityConverter {
    @Override
    public CityDTO toDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setDescription(city.getDescription());
        return cityDTO;
    }

    @Override
    public City fromDTO(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setDescription(cityDTO.getDescription());
        return city;
    }
}

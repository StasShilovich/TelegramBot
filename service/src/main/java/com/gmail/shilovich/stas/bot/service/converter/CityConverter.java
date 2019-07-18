package com.gmail.shilovich.stas.bot.service.converter;

import com.gmail.shilovich.stas.bot.repository.model.City;
import com.gmail.shilovich.stas.bot.service.model.CityDTO;

public interface CityConverter {

    CityDTO toDTO(City city);

    City fromDTO(CityDTO cityDTO);
}

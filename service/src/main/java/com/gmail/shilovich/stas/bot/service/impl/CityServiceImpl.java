package com.gmail.shilovich.stas.bot.service.impl;

import com.gmail.shilovich.stas.bot.repository.CityRepository;
import com.gmail.shilovich.stas.bot.repository.model.City;
import com.gmail.shilovich.stas.bot.service.CityService;
import com.gmail.shilovich.stas.bot.service.converter.CityConverter;
import com.gmail.shilovich.stas.bot.service.model.CityDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LogManager.getLogger(CityServiceImpl.class);
    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }

    @Override
    @Transactional
    public CityDTO getCity(Long id) {
        City city = cityRepository.findById(id);
        return cityConverter.toDTO(city);
    }

    @Override
    @Transactional
    public void addCity(CityDTO cityDTO) {
        City city = cityConverter.fromDTO(cityDTO);
        cityRepository.persist(city);
    }

    @Override
    @Transactional
    public void changeCity(CityDTO newCityDTO) {
        City city = cityRepository.findById(newCityDTO.getId());
        CityDTO oldCity = cityConverter.toDTO(city);
        logger.info(newCityDTO);
        if (!oldCity.equals(newCityDTO)) {
            City newCity = cityConverter.fromDTO(newCityDTO);
            cityRepository.updateCity(newCity);
        }
    }

    @Override
    @Transactional
    public void removeCity(Long id) {
        City city = cityRepository.findById(id);
        cityRepository.remove(city);
    }
}

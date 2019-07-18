package com.gmail.shilovich.stas.bot.service.impl;

import com.gmail.shilovich.stas.bot.repository.CityRepository;
import com.gmail.shilovich.stas.bot.repository.model.City;
import com.gmail.shilovich.stas.bot.service.BotService;
import com.gmail.shilovich.stas.bot.service.converter.CityConverter;
import com.gmail.shilovich.stas.bot.service.model.CityDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BotServiceImpl implements BotService {

    private static final Logger logger = LogManager.getLogger(BotServiceImpl.class);

    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    public BotServiceImpl(CityRepository cityRepository, CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }

    @Override
    @Transactional
    public String getCityInfo(String city) {
        City cityFound = cityRepository.findByName(city);
        CityDTO cityDTO = cityConverter.toDTO(cityFound);
        logger.info(cityDTO.toString());
        return cityDTO.getDescription();
    }

    @Override
    @Transactional
    public boolean isCityExists(String city) {
        List<CityDTO> cityDTOS = cityRepository.findAllEntites().stream()
                .map(cityConverter::toDTO)
                .collect(Collectors.toList());
        for (CityDTO cityDTO : cityDTOS) {
            String cityName = cityDTO.getName().toLowerCase();
            if (city.equals(cityName)) {
                return true;
            }
        }
        return false;
    }
}

package com.gmail.shilovich.stas.bot.repository;

import com.gmail.shilovich.stas.bot.repository.model.City;

public interface CityRepository extends GenericRepository<Long, City> {
    City findByName(String name);

    void updateCity(City newCity);

}

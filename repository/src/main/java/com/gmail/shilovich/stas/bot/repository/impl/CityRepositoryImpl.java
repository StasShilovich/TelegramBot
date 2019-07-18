package com.gmail.shilovich.stas.bot.repository.impl;

import com.gmail.shilovich.stas.bot.repository.CityRepository;
import com.gmail.shilovich.stas.bot.repository.exception.DatabaseException;
import com.gmail.shilovich.stas.bot.repository.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class CityRepositoryImpl extends GenericRepositoryImpl<Long, City> implements CityRepository {
    @Override
    public City findByName(String name) {
        String hql = "FROM City WHERE name=:name";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        return (City) query.getSingleResult();
    }

    @Override
    public void updateCity(City newCity) {
        String hql = "UPDATE City C SET name=:name, description=:description WHERE C.id=:id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", newCity.getName());
        query.setParameter("description", newCity.getDescription());
        query.setParameter("id", newCity.getId());
        int row = query.executeUpdate();
        if (row == 0) {
            throw new DatabaseException("Row affected " + row + " when " + newCity + " update.");
        }
    }
}

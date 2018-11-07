package com.voting.repository.datajpa;

import com.voting.model.Dish;
import com.voting.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository {
    @Autowired
    private CrudDishRepository crudDishRepository;

    @Override
    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.getOne(id);
    }

    @Override
    public List<Dish> getByName(String name) {
        return crudDishRepository.getByName(name);
    }

    @Override
    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }

    public List<Dish> findByNameAndPrice(String name, double price) {
        return crudDishRepository.findByNameAndPrice(name, price);
    }
}

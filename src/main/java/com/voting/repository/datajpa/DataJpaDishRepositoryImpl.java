package com.voting.repository.datajpa;

import com.voting.model.Dish;
import com.voting.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaDishRepositoryImpl implements DishRepository {
    @Autowired
    private CrudDishRepository crudDishRepository;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.findById(id);
    }

    @Override
    public List<Dish> getByName(String name) {
        return crudDishRepository.getByName(name.toUpperCase());
    }

    @Override
    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }

    public Dish getByNameAndPrice(String name, double price) {
        return crudDishRepository.findByNameAndPrice(name, price);
    }

}

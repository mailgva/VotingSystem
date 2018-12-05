package com.voting.service;

import com.voting.model.Dish;
import com.voting.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("datajpa")
public class DishServiceTest extends AbstractServiceTest{

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private DishService service;

    @Test
    public void create() {
        service.create(new Dish(null,"Пельмени", 50.0d));
    }

    @Test //(expected = NotFoundException.class)
    public void deleteWithNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(100004));
    }

    @Test
    public void delete() {
        service.delete(100025);
    }


    @Test
    public void get() {
        System.out.println(service.get(100025));
    }

    @Test
    public void getByName() {
        service.getByName("шеф").forEach(System.out::println);
    }

    @Test
    public void update() {
        Dish dish = service.get(100025);
        dish.setPrice(99.99);
        dish.setName("Царский");
        service.update(dish);
        System.out.println(service.get(100025));
    }

    @Test
    public void getAll() {
        service.getAll().forEach(System.out::println);
    }
}
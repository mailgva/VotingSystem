package com.voting.service;

import com.voting.model.Dish;
import com.voting.service.DishService;
import com.voting.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {

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

    @Test(expected = NotFoundException.class)
    public void deleteWithNotFound() {
        service.delete(100004);
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
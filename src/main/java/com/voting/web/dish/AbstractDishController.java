package com.voting.web.dish;

import com.voting.model.Dish;
import com.voting.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public List<Dish> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Dish get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }
}

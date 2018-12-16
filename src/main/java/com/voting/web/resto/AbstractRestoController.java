package com.voting.web.resto;

import com.voting.model.Dish;
import com.voting.model.Resto;
import com.voting.service.DishService;
import com.voting.service.RestoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractRestoController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestoService service;

    public List<Resto> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Resto get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }
}

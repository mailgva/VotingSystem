package com.voting.web.resto;

import com.voting.model.Resto;
import com.voting.service.RestoService;
import com.voting.web.dailyMenu.DailyMenuRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RestoRestController {
    private static final Logger log = LoggerFactory.getLogger(DailyMenuRestController.class);

    private final RestoService service;

    @Autowired
    public RestoRestController(RestoService service) {
        this.service = service;
    }

    public Resto create(Resto resto) {
        return service.create(resto);
    }


    public void delete(int id) {
        service.delete(id);
    }

    public Resto get(int id) {
        return service.get(id);
    }

    public Resto getByName(String name) {
        return service.getByName(name);
    }

    public void update(Resto resto) {
        service.update(resto);
    }

    public List<Resto> getAll() {
        return null;
    }
}

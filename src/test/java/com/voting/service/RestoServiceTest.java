package com.voting.service;

import com.voting.RestoTestData;
import com.voting.model.Resto;
import org.junit.jupiter.api.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("datajpa")
public class RestoServiceTest  extends AbstractServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private RestoService service;


    @Test
    public void create() {
        service.create(new Resto(null, "Ит квартал"));
        assertEquals(service.getAll().size(),6);
    }

    @Test
    public void delete() {
        service.delete(100004);
        assertEquals(service.getAll().size(), RestoTestData.restos.size()-1);
    }

    @Test
    public void get() {
        assertEquals(service.get(100003), RestoTestData.getById(100003));
    }

    @Test
    public void getByName() {
        String name = "Ресторан 3";
        assertEquals(service.getByName(name), RestoTestData.getByName(name));
    }

    @Test
    public void update() {
        Resto resto = service.get(100002);
        resto.setAddress("Дерибон");
        service.update(resto);
        assertEquals(service.get(100002), resto);
    }

    @Test
    public void getAll() {
        assertEquals(service.getAll().size(), RestoTestData.restos.size());
    }
}
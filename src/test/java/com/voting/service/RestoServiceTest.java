package com.voting.service;


import com.voting.model.Resto;
import com.voting.service.RestoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

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
    }

    @Test
    public void delete() {
        service.delete(100004);
    }

    @Test
    public void get() {
        System.out.println(service.get(100003));
    }

    @Test
    public void getByName() {
        System.out.println(service.getByName("Ресторан 3"));
    }

    @Test
    public void update() {
        Resto resto = service.get(100002);
        resto.setAddress("Дерибон");
        service.update(resto);
    }

    @Test
    public void getAll() {
        service.getAll().forEach(System.out::println);
    }
}
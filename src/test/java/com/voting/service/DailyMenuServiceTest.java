package com.voting.service;

import com.voting.model.DailyMenu;
import com.voting.model.Resto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

@ActiveProfiles("datajpa")
public class DailyMenuServiceTest extends AbstractServiceTest{

    @Autowired
    private DailyMenuService service;

    @Autowired
    private RestoService restoService;

    @Autowired
    private DishService dishService;

    @Test
    public void create() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2018");
        service.create(new DailyMenu(date, restoService.get(100003), dishService.get(100014)));
    }

    @Test
    public void update() {
        DailyMenu dailyMenu = service.get(100027);
        dailyMenu.setResto(restoService.get(100005));
        dailyMenu.setDish(dishService.get(100012));
        service.update(dailyMenu);
    }

    @Test
    public void delete() {
        service.delete(100028);
    }

    @Test
    public void get() {
        service.delete(100029);
    }

    @Test
    public void getByDate() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("01-10-2018");
        service.getByDate(date).forEach(System.out::println);
    }

    @Test
    public void getByNameResto() {
        service.getByNameResto("Ресторан 3").forEach(System.out::println);
    }

    @Test
    public void getAll() {
        service.getAll().forEach(System.out::println);
    }
}
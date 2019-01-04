package com.voting.service;

import com.voting.model.DailyMenu;
import com.voting.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.voting.UserTestData.USER;
import static com.voting.util.DailyMenuUtil.convertToDailyMenuTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
    @Transactional
    public void update() {
        DailyMenu dailyMenu = service.get(100040);
        dailyMenu.setResto(restoService.get(100005));
        dailyMenu.setDish(dishService.get(100012));
        service.update(dailyMenu);
        assertThat(service.get(100040)).isEqualToComparingFieldByField(dailyMenu);

    }

    @Test
    public void delete() {
        service.delete(100088);
    }

    @Test
    public void get() {
        service.delete(100050);
    }

    @Test
    public void getByDate() throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("07-12-2018");
        Vote vote = new Vote(USER, restoService.get(100003), date, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        //convertToDailyMenuTo(service.getByDate(date), vote)
        convertToDailyMenuTo(date, service.getByDate(date), vote)
                .forEach(System.out::println);

    }

    /*@Test
    public void getByNameResto() {
        service.getByNameResto("Ресторан 3").forEach(System.out::println);
    }*/

    @Test
    public void getAll() {
        assertEquals(service.getAll().size(), (6 * 3) + (restoService.getAll().size() * 5 *6)) ;
    }
}
package com.voting.service;

import com.voting.model.Resto;
import com.voting.model.User;
import com.voting.model.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    private VoteService service;


    @Autowired
    private UserService userService;

    @Autowired
    private RestoService restoService;


    @Test
    public void create() throws ParseException {
        User user = userService.get(100001);
        Resto resto = restoService.get(100003);

        Calendar calendar = new GregorianCalendar(2018,Calendar.NOVEMBER,02);
        Date date = calendar.getTime();
        Vote vote = new Vote(null, user, resto, date, LocalDateTime.now());
        System.out.println("=================================");
        System.out.println(vote);
        System.out.println("=================================");
        service.create(vote, 100001);
    }

    @Test
    public void update() {
    }


    @Test
    public void get() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getBetweenDateTimes() {
    }

    @Test
    public void getAll() {
    }


}
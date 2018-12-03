package com.voting.web.dailyMenu;

import com.voting.web.vote.VoteRestController;
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
import java.util.Date;

import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
//@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@ActiveProfiles({"datajpa","postgres"})
public class DailyMenuRestControllerTest {

    @Autowired
    private DailyMenuRestController controller;

    @Autowired
    private VoteRestController voteRestController;

    @Test
    public void get() throws ParseException {
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-11-11");
        DailyMenuUtil.convertToDailyMenuTo(controller.getByDate(date), voteRestController.getByDate(date)).forEach(System.out::println);
        */
    }


    @Test
    public void delete() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getByDate() {
    }
}
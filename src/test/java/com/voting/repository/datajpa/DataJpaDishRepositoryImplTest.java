package com.voting.repository.datajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles({"datajpa","postgres"})
public class DataJpaDishRepositoryImplTest {

    @Autowired
    private DataJpaDishRepositoryImpl repository;

    @Test
    public void findByNameAndPrice() {
       // repository.findByNameAndPrice("Пицца", 125).forEach(System.out::println);
    }
}
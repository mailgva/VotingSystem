package com.voting.repository.datajpa;

import com.voting.ActiveDbProfileResolver;
import com.voting.TimingExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
//@ExtendWith(SpringExtension.class)
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ExtendWith(TimingExtension.class)
//@ActiveProfiles({"datajpa","postgres"})
public class DataJpaDishRepositoryImplTest {

    @Autowired
    private DataJpaDishRepositoryImpl repository;

    @Test
    public void findByNameAndPrice() {
       // repository.findByNameAndPrice("Пицца", 125).forEach(System.out::println);
    }
}
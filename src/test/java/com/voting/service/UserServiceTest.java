package com.voting.service;

import com.voting.model.Role;
import com.voting.model.User;
import com.voting.util.exception.NotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
//import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.voting.UserTestData.*;
import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        //SLF4JBridgeHandler.install();
    }

    private static final Logger log = getLogger(UserServiceTest.class);

    private static List<String> testLog = new ArrayList<>();

    private static long startTest;


    @Rule
    public TestWatcher watcher = new TestWatcher() {
        long startMethodTest;

        private void toLog(String msg, long sec) {
            testLog.add(String.format("%s - %d sec", msg, sec));
        }

        @Override
        protected void starting(Description description) {
            startMethodTest = System.currentTimeMillis();
        }

        @Override
        protected void finished(Description description) {
            long testSec = System.currentTimeMillis() - startMethodTest;
            String methodName = description.getDisplayName().substring(0, description.getDisplayName().indexOf("("));
            toLog(methodName, testSec);
        }
    };


    @BeforeClass
    public static void init() {
        startTest = System.currentTimeMillis();
    }

    @AfterClass
    public static void destroy() {
        StringBuffer sb = new StringBuffer("***********RESULTS TESTS***********\n");
        testLog.forEach(s -> sb.append(s + "\n"));
        sb.append("All test: " + (System.currentTimeMillis() - startTest));
                sb.append("***********FINISH TESTS***********");
        log.debug("\n" + sb.toString());

    }

    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass",  false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deletedNotFound() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }
}
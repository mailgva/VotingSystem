package com.voting.web.dailyMenu;

import com.voting.model.DailyMenu;
import com.voting.service.DailyMenuService;
import com.voting.web.voting.VoteRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

import static com.voting.util.ValidationUtil.assureIdConsistent;
import static com.voting.util.ValidationUtil.checkNew;

@Controller
public class DailyMenuRestController {
    private static final Logger log = LoggerFactory.getLogger(DailyMenuRestController.class);

    private final DailyMenuService service;

    @Autowired
    public DailyMenuRestController(DailyMenuService service) {
        this.service = service;
    }

    public DailyMenu get(int id) {
        log.info("get DailyMenu {} ", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete DailyMenu {} ", id);
        service.delete(id);
    }
    /*
    public List<DailyMenu> getAll() {
        log.info("getAll ");
        return MealsUtil.getWithExcess(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }*/

    public DailyMenu create(DailyMenu dailyMenu) {
        checkNew(dailyMenu);
        log.info("create {}", dailyMenu);
        return service.create(dailyMenu);
    }

    public void update(DailyMenu dailyMenu, int id) {
        assureIdConsistent(dailyMenu, id);
        log.info("update {} ", dailyMenu);
        service.update(dailyMenu);
    }


    public List<DailyMenu> getByDate(Date date) {
        log.info("get DailyMenu for date {} ", date);
        return service.getByDate(date);
    }


    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    /*
    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Meal> mealsDateFiltered = service.getBetweenDates(
                orElse(startDate, DateTimeUtil.MIN_DATE), orElse(endDate, DateTimeUtil.MAX_DATE), userId);

        return MealsUtil.getFilteredWithExcess(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(),
                orElse(startTime, LocalTime.MIN), orElse(endTime, LocalTime.MAX)
        );
    }
    */
}
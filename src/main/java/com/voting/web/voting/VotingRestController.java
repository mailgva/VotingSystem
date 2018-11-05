package com.voting.web.voting;

import com.voting.model.Vote;
import com.voting.service.VoteService;
import com.voting.util.DateTimeUtil;
import com.voting.util.MealsUtil;
import com.voting.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.voting.util.ValidationUtil.assureIdConsistent;
import static com.voting.util.ValidationUtil.checkNew;

@Controller
public class VotingRestController {
    private static final Logger log = LoggerFactory.getLogger(VotingRestController.class);

    private final VoteService service;

    @Autowired
    public VotingRestController(VoteService service) {
        this.service = service;
    }

    public Vote get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }
    /*
    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return MealsUtil.getWithExcess(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }*/

    public Vote create(Vote vote) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        log.info("create {} for user {}", vote, userId);
        return service.create(vote, userId);
    }

    public void update(Vote vote, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, id);
        log.info("update {} for user {}", vote, userId);
        service.update(vote, userId);
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

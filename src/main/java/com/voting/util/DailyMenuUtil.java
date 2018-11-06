package com.voting.util;

import com.voting.model.DailyMenu;
import com.voting.model.Vote;
import com.voting.service.VoteService;
import com.voting.to.DailyMenuTo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.voting.web.SecurityUtil.authUserId;

public class DailyMenuUtil {

    @Autowired
    public static VoteService voteService;

    private DailyMenuUtil(){}

    public static List<DailyMenuTo> convertToDailyMenuTo(List<DailyMenu> dailyMenus, Vote vote) {
        int userId = authUserId();
        return dailyMenus.stream()
                .map(dm -> createWithUser(dm, vote))
                .collect(Collectors.toList());
    }

    public static DailyMenuTo createWithUser(DailyMenu dailyMenu, Vote vote) {
        return new DailyMenuTo(dailyMenu.getResto(),dailyMenu.getDish(),
                (vote.getResto().getId() == dailyMenu.getResto().getId() &&
                            vote.getUser().getId() == vote.getUser().getId() ? true : false));

    }
}

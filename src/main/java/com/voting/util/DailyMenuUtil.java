package com.voting.util;

import com.voting.model.DailyMenu;
import com.voting.model.Vote;
import com.voting.to.DailyMenuTo;

import java.util.List;
import java.util.stream.Collectors;


public class DailyMenuUtil {

    private DailyMenuUtil(){}

    public static List<DailyMenuTo> convertToDailyMenuTo(List<DailyMenu> dailyMenus, Vote vote) {
        return dailyMenus.stream()
                .map(dm -> createWithUser(dm, vote))
                .collect(Collectors.toList());
    }

    public static DailyMenuTo createWithUser(DailyMenu dailyMenu, Vote vote) {
        return new DailyMenuTo(dailyMenu.getResto(),dailyMenu.getDish(),
                (vote == null ? false :
                    dailyMenu.getResto().getId().compareTo(vote.getResto().getId()) == 0) );

    }
}

package com.voting.service;

import com.voting.model.DailyMenu;
import com.voting.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface DailyMenuService {
    DailyMenu create(DailyMenu dailyMenu);

    void update(DailyMenu dailyMenu);

    void delete(int id) throws NotFoundException;

    DailyMenu get(int id) throws NotFoundException;

    List<DailyMenu> getByDate(Date date) ;

    List<DailyMenu> getByNameResto(String nameResto);

    List<DailyMenu> getAll();
}

package com.voting.repository.datajpa;

import com.voting.model.DailyMenu;
import com.voting.repository.DailyMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public class DataJpaDailyMenuRepositoryImpl implements DailyMenuRepository {
    private static final Sort SORT_DATE = new Sort(Sort.Direction.DESC, "date");
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC,  "resto.name");

    @Autowired
    private CrudDailyMenuRepository crudDailyMenuRepository;

    @Override
    public DailyMenu save(DailyMenu dailyMenu) {
        return crudDailyMenuRepository.save(dailyMenu);
    }

    @Override
    public boolean delete(int id) {
        return crudDailyMenuRepository.delete(id) != 0;
    }

    @Override
    public DailyMenu get(int id) {
        return crudDailyMenuRepository.getOne(id);
    }

    @Override
    public List<DailyMenu> getByDate(Date date) {
        return crudDailyMenuRepository.getByDate(date);
    }

    @Override
    public List<DailyMenu> getByNameResto(String nameResto) {
        return crudDailyMenuRepository.findAllByNameResto(nameResto);
    }

    @Override
    public List<DailyMenu> getAll() {
        return crudDailyMenuRepository.getAllOrderByDateDescAndOrderByNameRestoAsc();
    }
}

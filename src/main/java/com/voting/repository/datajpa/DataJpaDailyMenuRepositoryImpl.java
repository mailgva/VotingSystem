package com.voting.repository.datajpa;

import com.voting.model.DailyMenu;
import com.voting.repository.DailyMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaDailyMenuRepositoryImpl implements DailyMenuRepository {
    private static final Sort SORT_DATE = new Sort(Sort.Direction.DESC, "date");
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC,  "resto.name");

    @Autowired
    private CrudDailyMenuRepository crudDailyMenuRepository;

    @Override
    @Transactional
    public DailyMenu save(DailyMenu dailyMenu) {
        return crudDailyMenuRepository.save(dailyMenu);
    }

    @Override
    @Transactional
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
        return null; // crudDailyMenuRepository.findAllByNameResto(nameResto);
    }

    @Override
    public List<DailyMenu> getAll() {
        return crudDailyMenuRepository.findAll(); // crudDailyMenuRepository.getAllOrderByDateDescAndOrderByNameRestoAsc();
    }

    @Override
    @Transactional
    public void deleteByDate(Date date) {
       crudDailyMenuRepository.deleteByDate(date);
    }

    @Override
    @Transactional
    public void generateDailyMenu(Date date) {
        crudDailyMenuRepository.generateDailyMenu(date, date);
    }
}

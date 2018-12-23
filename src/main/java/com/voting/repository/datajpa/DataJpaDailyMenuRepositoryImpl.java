package com.voting.repository.datajpa;

import com.voting.model.DailyMenu;
import com.voting.repository.DailyMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
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
        return null; // crudDailyMenuRepository.getAllOrderByDateDescAndOrderByNameRestoAsc();
    }

    @Override
    @Transactional
    public void deleteByDate(Date date) {
        System.out.println("==================BEFORE DELETE");
        try {
            crudDailyMenuRepository.deleteByDate(date);
        } catch (Exception e) {
            System.out.println("==============ERROR DELETE=============");
            System.out.println(e.getLocalizedMessage());
            System.out.println("==============ERROR END=============");
        }

    }

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void generateDailyMenu(Date date) {
        System.out.println("==================BEFORE GENERATE");
        try {
            SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
            crudDailyMenuRepository.generateDailyMenu(sdf.format(date), sdf.format(date));

        } catch (Exception e) {
            System.out.println("==============ERROR GENERATE=============");
            System.out.println(e.getLocalizedMessage());
            System.out.println("==============ERROR END=============");
        }
       /* try {
            System.out.println("==================BEFORE GENERATE");
            SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
            *//*Query query = em.createQuery("SELECT generatedailymenu('" + sdf.format(date) + "', '" + sdf.format(date) + "')");
            query.executeUpdate();*//*

            em.createStoredProcedureQuery(DailyMenu.GENERATE_DAILY_MENU)
                    .s
                    .setParameter(1, sdf.format(date))
                    .setParameter(2, sdf.format(date))
                    .executeUpdate();

           *//* em.createStoredProcedureQuery("generatedailymenu")
                    .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
                    .setParameter(1, date)
                    .setParameter(2, date)
                    .execute();*//*
        } catch (Exception e) {
            System.out.println("==============ERROR=============");
            System.out.println(e.getLocalizedMessage());
            System.out.println("================================");
        }*/

    }
}

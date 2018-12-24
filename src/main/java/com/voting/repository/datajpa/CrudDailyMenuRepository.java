package com.voting.repository.datajpa;

import com.voting.model.DailyMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    @Transactional
    DailyMenu save(DailyMenu dailyMenu);

    @Transactional
    @Modifying
    @Query("DELETE FROM DailyMenu dm WHERE dm.id=:id")
    int delete(@Param("id") int id);


    @Query("SELECT dm FROM DailyMenu dm JOIN FETCH dm.resto JOIN FETCH dm.dish WHERE dm.date=:date ORDER BY dm.resto.name, dm.dish.name")
    List<DailyMenu> getByDate(@Param("date") Date date);

    @Override
    DailyMenu getOne(Integer integer);

    //List<DailyMenu> findAllByNameResto(String nameResto);


    //List<DailyMenu> getAllOrderByDateDescAndOrderByNameRestoAsc();

    //@Procedure("GenerateDailyDishes(:date, :date)")


    @Transactional
    @Procedure(name = DailyMenu.GENERATE_DAILY_DISHES)
    void generateDailyMenu(@Param("fromdate") Date fromdate, @Param("todate") Date todate);

    /*@Transactional
    @Procedure(name = DailyMenu.GENERATE_DAILY_MENU)
    void generateDailyMenu(@Param("fromdate") String fromdate, @Param("todate") String todate);*/


    @Transactional
    @Modifying
    @Query("DELETE FROM DailyMenu dm WHERE dm.date=:date")
    void deleteByDate(@Param("date") Date date);
}

package com.voting.repository.jpa;

import com.voting.model.DailyMenu;
import com.voting.repository.DailyMenuRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDailyMenuRepositoryImpl implements DailyMenuRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public DailyMenu save(DailyMenu dailyMenu) {
        if (dailyMenu.isNew()) {
            em.persist(dailyMenu);
            return dailyMenu;
        } else {
            return em.merge(dailyMenu);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(DailyMenu.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public DailyMenu get(int id) {
        List<DailyMenu> dailyMenu = em.createNamedQuery(DailyMenu.GET)
                .setParameter("id", id)
                .getResultList();
        return DataAccessUtils.singleResult(dailyMenu);
   }

    @Override
    public List<DailyMenu> getByDate(Date date) {
        return em.createNamedQuery(DailyMenu.GET_BY_DATE, DailyMenu.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<DailyMenu> getByNameResto(String nameResto) {
        return em.createNamedQuery(DailyMenu.GET_BY_NAME_RESTO, DailyMenu.class)
                .setParameter("name", nameResto.toUpperCase())
                .getResultList();

    }

    @Override
    public List<DailyMenu> getAll() {
        return em.createNamedQuery(DailyMenu.GET_ALL, DailyMenu.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteByDate(Date date) {
        em.createQuery("DELETE FROM daily_menu dm WHERE dm.date = :date")
                .setParameter("date", date)
                .executeUpdate();
    }

    @Override
    public void generateDailyMenu(Date date) {
        em.createStoredProcedureQuery("GenerateDailyDishes")
                .registerStoredProcedureParameter(1, Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Date.class, ParameterMode.IN)
                .setParameter(1, date)
                .setParameter(2, date);
    }
}

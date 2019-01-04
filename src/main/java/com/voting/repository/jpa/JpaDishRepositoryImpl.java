package com.voting.repository.jpa;

import com.voting.model.Dish;
import com.voting.repository.DishRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Dish save(Dish dish) {
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Dish get(int id) {
        return em.find(Dish.class, id);
    }

    @Override
    public List<Dish> getByName(String name) {
        return em.createNamedQuery(Dish.GET_BY_NAME, Dish.class)
                .setParameter("partName", name.toUpperCase())
                .getResultList();
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.ALL_SORTED, Dish.class).getResultList();
    }

    @Override
    public Dish getByNameAndPrice(String name, double price) {
        return null;
    }
}

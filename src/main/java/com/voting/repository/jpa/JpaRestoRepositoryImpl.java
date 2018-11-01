package com.voting.repository.jpa;

import com.voting.model.Resto;
import com.voting.repository.RestoRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaRestoRepositoryImpl implements RestoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Resto save(Resto resto) {
        if (resto.isNew()) {
            em.persist(resto);
            return resto;
        } else {
            return em.merge(resto);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createQuery("DELETE FROM Resto r WHERE r.id=:id")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Resto get(int id) {
        return em.find(Resto.class, id);
    }

    @Override
    public Resto getByName(String name) {
        List<Resto> resto = em.createNamedQuery(Resto.GET_BY_NAME, Resto.class)
                .setParameter("name", name)
                .getResultList();
        return DataAccessUtils.singleResult(resto);
    }

    @Override
    public List<Resto> getAll() {
        return em.createNamedQuery(Resto.GET_ALL, Resto.class).getResultList();
    }
}

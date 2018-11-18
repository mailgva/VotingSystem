package com.voting.repository.jpa;

import com.voting.model.Resto;
import com.voting.model.User;
import com.voting.model.Vote;
import com.voting.repository.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(em.getReference(User.class, userId));
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        Vote vote = em.createQuery("SELECT v FROM Vote v " +
                "JOIN FETCH v.resto JOIN FETCH v.user WHERE v.id = :id AND v.user.id=:userId", Vote.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getSingleResult();
        return vote.getUser().getId() == userId ? vote : null;
    }

    @Override
    public List<Vote> getAll(int userId) {
        return em.createNamedQuery(Vote.ALL_SORTED, Vote.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Vote.GET_BETWEEN, Vote.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public Vote getByDate(Date date, int userId) {
        return em.createNamedQuery(Vote.GET_USER_BY_DATE, Vote.class)
                .setParameter("userId", userId)
                .setParameter("date", date)
                //.getSingleResult()
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Vote> getAllByDate(Date date) {
        return em.createNamedQuery(Vote.GET_ALL_BY_DATE, Vote.class)
                .setParameter("date", date)
                .getResultList();
    }
}

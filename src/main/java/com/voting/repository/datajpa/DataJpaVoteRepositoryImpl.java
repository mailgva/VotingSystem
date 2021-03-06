package com.voting.repository.datajpa;

import com.voting.model.Vote;
import com.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        return crudVoteRepository.save(vote);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.get(id);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public Vote getByDate(Date date, int userId) {
        return crudVoteRepository.getByDate(date, userId);
    }

    @Override
    public List<Vote> getAllByDate(Date date) {
        return crudVoteRepository.getAllByDate(date);
    }

    @Override
    public List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}

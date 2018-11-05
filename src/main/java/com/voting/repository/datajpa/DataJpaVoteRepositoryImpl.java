package com.voting.repository.datajpa;

import com.voting.model.Vote;
import com.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Override
    public Vote save(Vote vote, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId);
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.get(id, userId);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return crudVoteRepository.getAll(userId);
    }

    @Override
    public List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudVoteRepository.getBetween(startDate, endDate, userId);
    }

    @Override
    public List<Vote> getUserByDate(Date date, int userId) {
        return crudVoteRepository.getUserByDate(date, userId);
    }

    @Override
    public List<Vote> getAllByDate(Date date) {
        return crudVoteRepository.getAllByDate(date);
    }
}

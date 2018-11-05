package com.voting.repository.datajpa;

import com.voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    // null if updated meal do not belong to userId
    Vote save(Vote vote, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll(int userId);

    // ORDERED dateTime desc
    List<Vote> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    List<Vote> getUserByDate(Date date, int userId);

    List<Vote> getAllByDate(Date date);
}

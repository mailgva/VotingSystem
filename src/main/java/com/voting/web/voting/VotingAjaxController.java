package com.voting.web.voting;

import com.voting.to.DailyMenuTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(VotingAjaxController.REST_URL)
public class VotingAjaxController extends AbstractVotingController {
    static final String REST_URL = "/ajax/voting";

    @Override
    @GetMapping
    public List<DailyMenuTo> getDailyMenu(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return super.getDailyMenu(date);
    }

    @PostMapping
    public void setUserVote(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                            @RequestParam(value = "restoId") Integer restoId,
                            @RequestParam(value = "voteId") Integer voteId) {
        super.setUserVote(date, restoId, voteId);
    }

}

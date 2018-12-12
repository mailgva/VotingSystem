package com.voting.web.voting;

import com.voting.to.DailyMenuTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = VotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingRestController extends AbstractVotingController {
    static final String REST_URL = "/rest/voting";

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<DailyMenuTo> getDailyMenu(@RequestParam(value = "date")  Date date) {
        return super.getDailyMenu(date);
    }

    @PostMapping
    public void setUserVote(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                            @RequestParam(value = "restoId") Integer restoId) {
        super.setUserVote(date, restoId);
    }

}

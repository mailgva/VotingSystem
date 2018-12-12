package com.voting.web.voting;

import com.voting.to.DailyMenuTo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = VotingAjaxController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingAjaxController extends AbstractVotingController {
    static final String REST_URL = "/ajax/voting";

    @Override
    @GetMapping
    public List<DailyMenuTo> getDailyMenu(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return super.getDailyMenu(date);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<String> setVote(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                            @RequestParam(value = "restoId") Integer restoId) {
        try {
            super.setUserVote(date, restoId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

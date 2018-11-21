package com.voting.web;

import com.voting.model.Resto;
import com.voting.model.Vote;
import com.voting.service.DailyMenuService;
import com.voting.service.RestoService;
import com.voting.service.UserService;
import com.voting.service.VoteService;
import com.voting.util.DailyMenuUtil;
import com.voting.util.MealsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;


@RequestMapping(value = "/voting")
@Controller
public class JspVotiningController {

    @Autowired
    private VoteService service;

    @Autowired
    private DailyMenuService dailyMenuService;

    @Autowired
    private RestoService restoService;

    @Autowired
    private UserService userService;


    @GetMapping("")
    public String getDailyMenu(HttpServletRequest request, Model model) {
        return getDailyMenuByDate(request, model);
    }


    @PostMapping("")
    public String getDailyMenuFiltered(HttpServletRequest request, Model model)  {
        return getDailyMenuByDate(request, model);
    }

    private String getDailyMenuByDate(HttpServletRequest request, Model model) {
        int userId = SecurityUtil.authUserId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        if(request.getParameter("date") != null) {
            try {
                date = sdf.parse(request.getParameter("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(date==null) date = new Date();

        Vote vote = service.getByDate(date, userId);

        model.addAttribute("voteId", (vote == null ? null : vote.getId()));
        model.addAttribute("dateMenu", sdf.format(date));
        model.addAttribute("dailyMenus",
                DailyMenuUtil.convertToDailyMenuTo(dailyMenuService.getByDate(date), vote));
        return "dailymenu";
    }

    @PostMapping("/vote")
    public String setVote(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int userId = SecurityUtil.authUserId();
        Resto resto = restoService.get(Integer.parseInt(request.getParameter("restoId")));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date date = null;
        if(request.getParameter("date") != null) {
            try {
                date = sdf.parse(request.getParameter("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Vote vote = new Vote(
                (request.getParameter("voteId").isEmpty() ? null : Integer.parseInt(request.getParameter("voteId"))),
                userService.get(userId),
                resto,
                date,
                LocalDateTime.now());

        if (request.getParameter("voteId").isEmpty()) {
            service.create(vote, userId);
        } else {
            service.update(vote, userId);
        }
        model.addAttribute("dateMenu", sdf.format(date));
        return "forward:/voting";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

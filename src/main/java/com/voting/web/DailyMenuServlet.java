package com.voting.web;

import com.voting.model.Resto;
import com.voting.model.Vote;
import com.voting.util.DailyMenuUtil;
import com.voting.web.dailyMenu.DailyMenuRestController;
import com.voting.web.resto.RestoRestController;
import com.voting.web.voting.VoteRestController;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class DailyMenuServlet extends HttpServlet {
    private static final Logger log = getLogger(DailyMenuServlet.class);

    private ConfigurableApplicationContext springContext;
    private DailyMenuRestController dailyMenuRestController;
    private VoteRestController voteRestController;
    private RestoRestController restRestController;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "postgres, datajpa");
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");

        dailyMenuRestController = springContext.getBean(DailyMenuRestController.class);
        voteRestController = springContext.getBean(VoteRestController.class);
        restRestController = springContext.getBean(RestoRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date date = null;
        if(request.getParameter("date") != null) {
            try {
                date = sdf.parse(request.getParameter("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        /*
        System.out.println("===============");
        request.getParameterMap().forEach((k,v)->System.out.println("Item : " + k + " Value : " + v));
        System.out.println("===============");*/

        Vote vote;
        if("vote".equals(action)) {

            Resto resto = restRestController.get(Integer.parseInt(request.getParameter("restoId")));
            vote = new Vote(
                    resto,
                    date,
                    LocalDateTime.now());

            if (request.getParameter("voteId").isEmpty()) {
                voteRestController.create(vote);
            } else {
                voteRestController.update(vote, Integer.parseInt(request.getParameter("voteId")));
            }
        }

        if(date==null) {
            date = new Date();
            Calendar c = Calendar.getInstance();
            if(LocalDateTime.now().getHour() >= 11) {
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                date = c.getTime();
            }
        }
        vote = voteRestController.getByDate(date);
        request.setAttribute("voteId", (vote == null ? null : vote.getId()));
        request.setAttribute("dateMenu", sdf.format(date));
        request.setAttribute("dailyMenus",
                DailyMenuUtil.convertToDailyMenuTo(dailyMenuRestController.getByDate(date), vote));
        request.getRequestDispatcher("/dailymenu.jsp").forward(request, response);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        log.info("DATE_PARAM = " + request.getParameter("date"));

        Date date = null;
        if(request.getParameter("date") != null) {
            try {
                date = sdf.parse(request.getParameter("date"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(date==null) {
            date = new Date();
            Calendar c = Calendar.getInstance();
            if(LocalDateTime.now().getHour() >= 11) {
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                date = c.getTime();
            }
        }

        Vote vote = voteRestController.getByDate(date);

        switch (action == null ? "all" : action) {
            case "all":
            default:
                request.setAttribute("voteId", (vote == null ? null : vote.getId()));
                request.setAttribute("dateMenu", sdf.format(date));
                request.setAttribute("dailyMenus", DailyMenuUtil.convertToDailyMenuTo(dailyMenuRestController.getByDate(date), vote));
                request.getRequestDispatcher("/dailymenu.jsp").forward(request, response);
                break;
        }


    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

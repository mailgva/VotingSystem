package com.voting.web;

import com.voting.util.DailyMenuUtil;
import com.voting.web.dailyMenu.DailyMenuRestController;
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
import java.util.Date;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class DailyMenuServlet extends HttpServlet {
    private static final Logger log = getLogger(DailyMenuServlet.class);

    private ConfigurableApplicationContext springContext;
    private DailyMenuRestController dailyMenuRestController;
    private VoteRestController voteRestController;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "postgres, datajpa");
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");

        dailyMenuRestController = springContext.getBean(DailyMenuRestController.class);
        voteRestController =springContext.getBean(VoteRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("DOPOST IN DailyMenuServlet");
        request.setCharacterEncoding("UTF-8");
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
        if(date==null) date = new Date();

        request.setAttribute("dateMenu", sdf.format(date));
        request.setAttribute("dailyMenus",
                DailyMenuUtil.convertToDailyMenuTo(dailyMenuRestController.getByDate(date), voteRestController.getByDate(date)));
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
        if(date==null) date = new Date();

        switch (action == null ? "all" : action) {
            case "all":
            default:
                request.setAttribute("dateMenu", sdf.format(date));
                request.setAttribute("dailyMenus", DailyMenuUtil.convertToDailyMenuTo(dailyMenuRestController.getByDate(date), voteRestController.getByDate(date)));
                request.getRequestDispatcher("/dailymenu.jsp").forward(request, response);
                break;
        }


        /*
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                mealController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("meals", mealController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
        */
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}

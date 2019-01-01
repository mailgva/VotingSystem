package com.voting.web;

import com.voting.AuthorizedUser;
import com.voting.to.UserTo;
import com.voting.util.UserUtil;
import com.voting.web.user.AbstractUserController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static com.voting.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;

@Controller
public class RootController extends AbstractUserController {
    @GetMapping("/")
    public String root() {
        return "redirect:voting";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model = setModelAttrs(model);
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/voting")
    public String voting(Model model) {
        model.addAttribute("date", LocalDate.now().plusDays((LocalDateTime.now().getHour() < 11 ? 0 : 1)).toString());
        model = setModelAttrs(model);
        return "dailymenu";
    }

    @GetMapping("/dishes")
    public String dishes(Model model) {
        model = setModelAttrs(model);
        return "dishes";
    }

    @GetMapping("/restaurants")
    public String restaurants(Model model) {
        model = setModelAttrs(model);
        return "restaurants";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authUser) {
        model.addAttribute("userTo", authUser.getUserTo());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authUser) {
        if (result.hasErrors()) {
            return "profile";
        }
        try {
            super.update(userTo, authUser.getId());
            authUser.update(userTo);
            status.setComplete();
            return "redirect:voting";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            return "profile";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        }
        try {
            super.create(UserUtil.createNewFromTo(userTo));
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + userTo.getEmail();
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            model.addAttribute("register", true);
            return "profile";
        }
    }

    private Model setModelAttrs(Model m) {
        m.addAttribute("isAdmin", SecurityUtil.isAdmin());
        m.addAttribute("userName", SecurityUtil.authUserName());
        return m;
    }
}

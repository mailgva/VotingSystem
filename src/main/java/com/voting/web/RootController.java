package com.voting.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
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


    private Model setModelAttrs(Model m) {
        m.addAttribute("isAdmin", SecurityUtil.isAdmin());
        m.addAttribute("userName", SecurityUtil.authUserName());
        return m;
    }
}

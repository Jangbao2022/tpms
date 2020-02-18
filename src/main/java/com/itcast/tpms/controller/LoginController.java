package com.itcast.tpms.controller;

import com.itcast.tpms.model.User;
import com.itcast.tpms.service.loginservice.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response) {
        User checkedUser = loginService.checkUser(user);
        if (checkedUser == null) {
            return "login";
        } else {
            loginService.login(request, response, checkedUser);
        }
        return "redirect:/index";
    }

    @RequestMapping("/logon")
    public String logon(HttpServletRequest request, HttpServletResponse response) {
        loginService.logon(request, response, null);
        return "login";
    }

}

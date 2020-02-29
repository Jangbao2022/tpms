package com.itcast.tpms.controller;

import com.itcast.tpms.enums.UserPowerEnum;
import com.itcast.tpms.model.User;
import com.itcast.tpms.service.loginservice.ILoginService;
import com.itcast.tpms.service.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request,
                        HttpServletResponse response, Model model) {
        User checkedUser = loginService.checkUser(user);
        if (checkedUser == null) {
            model.addAttribute("message", "用户名密码错误");
            return "login";
        } else {
            loginService.login(request, response, checkedUser);
        }
        return "redirect:/index";
    }

    @RequestMapping("/registerPage")
    public String registerPage() {
        return "registerPage";
    }

    @RequestMapping("/register")
    public String register(User user, Model model) {
        user.setPower(UserPowerEnum.STUDENT.getPower());
        boolean b = userService.checkAccount(user);
        if (b) {
            userService.addOrUpdateUser(user);
        } else {
            model.addAttribute("message", "账号已存在");
            return "registerPage";
        }
        return "login";
    }

    @RequestMapping("/logon")
    public String logon(HttpServletRequest request, HttpServletResponse response) {
        loginService.logon(request, response, null);
        return "login";
    }

}

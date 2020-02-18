package com.itcast.tpms.controller;

import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.UserExp;
import com.itcast.tpms.model.Course;
import com.itcast.tpms.model.User;
import com.itcast.tpms.service.profileservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("upMyData")
    public String upMyData(User user, HttpServletRequest request) {
        UserExp userExp = (UserExp) request.getSession().getAttribute("userExp");
        User preUser = userExp.getUser();
        boolean updateSuccess = userService.updateUser(preUser, user);
        if (updateSuccess) {
            return "profile";
        }
        return "index";
    }

    @RequestMapping("getAllUerByPage")
    public String getAllUerByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        List<User> users = userService.getUserBypage(page, PageLimitEnum.USER_LIMIT.getLimit());
        model.addAttribute("users", users);
        return "users";
    }
}

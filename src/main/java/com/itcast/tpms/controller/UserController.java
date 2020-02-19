package com.itcast.tpms.controller;

import com.itcast.tpms.dto.PageDto;
import com.itcast.tpms.enums.PageLimitEnum;
import com.itcast.tpms.exp.UserExp;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.User;
import com.itcast.tpms.service.majorService.IMajorService;
import com.itcast.tpms.service.userservice.IUserService;
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

    @Autowired
    private IMajorService majorService;

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

    @RequestMapping("getUerByPage")
    public String getUerByPage(Integer page, Model model) {
        if (page == null || page < 0) {
            page = 0;
        }
        PageDto<UserExp> userExpPageDto = userService.getUserExpBypage(page, PageLimitEnum.USER_LIMIT.getLimit());
        model.addAttribute("pageDto", userExpPageDto);
        return "users";
    }

    @RequestMapping("addUserPage")
    public String addUserPage(Model model) {
        List<Major> allMajor = majorService.getAllMajor();
        model.addAttribute("majors", allMajor);
        return "IPUserPage";
    }

    @RequestMapping("updateUserPageById")
    public String updateUserPageById(Long userId, Model model) {
        User user = userService.getUserById(userId);
        List<Major> allMajor = majorService.getAllMajor();
        model.addAttribute("majors", allMajor);
        model.addAttribute("user", user);
        return "IPUserPage";
    }

    @RequestMapping("addOrUpdateUser")
    public String addOrUpdateUser(User user) {

        userService.addOrUpdateUser(user);
        return "redirect:/user/getUerByPage";
    }

    @RequestMapping("deleteUserById")
    public String deleteUserById(Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/user/getUerByPage";
    }
}

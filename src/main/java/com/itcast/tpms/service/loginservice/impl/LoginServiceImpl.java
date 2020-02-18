package com.itcast.tpms.service.loginservice.impl;

import com.itcast.tpms.enums.UserPowerEnum;
import com.itcast.tpms.exp.UserExp;
import com.itcast.tpms.mapper.MajorMapper;
import com.itcast.tpms.mapper.UserMapper;
import com.itcast.tpms.model.Major;
import com.itcast.tpms.model.User;
import com.itcast.tpms.model.UserExample;
import com.itcast.tpms.service.loginservice.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public User checkUser(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountEqualTo(user.getAccount())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 用户转用户增强类
     *
     * @param user
     * @return
     */
    private UserExp userToUserExp(User user) {
        UserExp userExp = new UserExp();
        if (UserPowerEnum.STUDENT.getPower().equals(user.getPower())) {
            userExp.setStudent(true);
            Major major = majorMapper.selectByPrimaryKey(user.getMajorId());
            userExp.setMajor(major);
        } else {
            userExp.setStudent(false);
        }
        userExp.setUser(user);
        String title = UserPowerEnum.getDescribe(userExp.getUser().getPower());
        userExp.setTitle(title);
        return userExp;
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response, User user) {

        UserExp userExp = userToUserExp(user);
        request.getSession().setAttribute("userExp", userExp);
        Cookie cookie = new Cookie("account", userExp.getUser().getAccount());
        cookie.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cookie);
    }

    @Override
    public void logon(HttpServletRequest request, HttpServletResponse response, User user) {
        request.getSession().removeAttribute("userExp");
        Cookie cookie = new Cookie("account", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}

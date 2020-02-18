package com.itcast.tpms.service.loginservice;

import com.itcast.tpms.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录service
 */
public interface ILoginService {

    User checkUser(User user);

    void login(HttpServletRequest request, HttpServletResponse response, User user);

    void logon(HttpServletRequest request, HttpServletResponse response, User user);
}

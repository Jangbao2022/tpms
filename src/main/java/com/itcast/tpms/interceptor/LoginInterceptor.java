package com.itcast.tpms.interceptor;

import com.itcast.tpms.exp.UserExp;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserExp userExp = (UserExp) request.getSession().getAttribute("userExp");
        if (userExp != null) {
            Cookie[] cookies = request.getCookies();
            String account = null;
            for (Cookie cookie : cookies) {
                if ("account".equals(cookie.getName())) {
                    account = cookie.getValue();
                    break;
                }
            }
            if (account.equals(userExp.getUser().getAccount())) {
                return true;
            }
        }
        request.getRequestDispatcher("/page/login").forward(request, response);
        return false;
    }
}

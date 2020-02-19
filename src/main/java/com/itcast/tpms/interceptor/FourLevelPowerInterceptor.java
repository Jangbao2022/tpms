package com.itcast.tpms.interceptor;

import com.itcast.tpms.enums.UserPowerEnum;
import com.itcast.tpms.exp.UserExp;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截教研处人员
 */
public class FourLevelPowerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserExp userExp = (UserExp) request.getSession().getAttribute("userExp");
        Integer power = userExp.getUser().getPower();
        if (UserPowerEnum.SUPER_TEACHER.getPower().equals(power)) {
            request.getSession().setAttribute("errorMessage", "您的权限不够");
            response.sendRedirect("/error");
            return false;
        }
        return true;
    }
}

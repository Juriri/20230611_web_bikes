package com.user.session;

import com.user.member.userDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.ognl.ObjectNullHandler;
import org.springframework.web.servlet.HandlerInterceptor;

//로그인(세션) 체크용 인터셉터 추가하기
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        userDTO userDTO = (userDTO) session.getAttribute("loginMember");

        //로그인이 되어있지않은 생태
        if(userDTO == null){
            response.sendRedirect("/user_login");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }
}

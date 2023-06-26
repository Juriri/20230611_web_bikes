package com.user.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/loginclick")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user_id");
        String password = request.getParameter("user_password");

        // 로그인 로직: 사용자 인증 및 세션 생성
        if (authenticate(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

            response.sendRedirect("/"); // 로그인 성공 후 이동할 페이지
        } else {
            response.sendRedirect("/login?error=true"); // 로그인 실패 시 다시 로그인 페이지로 리다이렉트
        }
    }

    private boolean authenticate(String username, String password) {
        // 사용자명과 비밀번호를 데이터베이스 또는 다른 인증 소스에서 확인하는 로직을 구현해야 합니다.
        // 예시로 사용자명이 "admin"이고 비밀번호가 "password"인 경우에만 인증 성공으로 가정합니다.

        // 예시: 하드코딩된 사용자 정보와 비교하여 인증 여부 확인
        String storedUsername = "hjlee";
        String storedPassword = "1234";

        return username.equals(storedUsername) && password.equals(storedPassword);
    }
}


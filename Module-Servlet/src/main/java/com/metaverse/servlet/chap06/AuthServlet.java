package com.metaverse.servlet.chap06;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // 세션이 없으면 새로 생성
        String existingUser = (String) session.getAttribute("loggedInUser");

        session.setAttribute("loggedInUser", existingUser);
        /* auth.jsp에서 로그인 정보 확인*/
        RequestDispatcher dispatcher = req.getRequestDispatcher("/chap06/auth.jsp");
        dispatcher.forward(req, resp);
    }
}
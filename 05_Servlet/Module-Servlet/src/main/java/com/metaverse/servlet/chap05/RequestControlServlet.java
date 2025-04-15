package com.metaverse.servlet.chap05;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/request")
public class RequestControlServlet extends HttpServlet {

    private List<String> users = new ArrayList<>(); // 간단한 사용자 저장소 (실제로는 DB)

    @Override
    public void init(ServletConfig config) throws ServletException {
        users.add("임플란트키드");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("redirect를 통해 요청을 통해 register-form으로 전달.");
        // 회원가입 폼으로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/chap05/register-form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String email = req.getParameter("email"); // 추가된 사용자 이름 필드

        if (username != null && !username.isEmpty() && email != null && !email.isEmpty()) {
            if (users.contains(username)) {
                // 중복 사용자: 리다이렉트로 가입 폼으로 돌아가기
                HttpSession session = req.getSession();
                session.setAttribute("error", "이미 존재하는 사용자입니다: " + username);
                resp.sendRedirect(req.getContextPath() + "/request");
            } else {
                // 회원가입 성공: 사용자 추가 후 포워드로 결과 페이지 이동
                users.add(username);
                req.setAttribute("message", "회원가입 성공! 사용자: " + username);
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/chap05/register-result.jsp");
                dispatcher.forward(req, resp);
            }
        } else {
            // 입력 오류: 리다이렉트로 가입 폼으로 돌아가기
            HttpSession session = req.getSession();
            session.setAttribute("error", "사용자 이름과 이메일을 모두 입력해주세요!");
            resp.sendRedirect(req.getContextPath() + "/request");
            //doGet(req, resp);
        }
    }
}
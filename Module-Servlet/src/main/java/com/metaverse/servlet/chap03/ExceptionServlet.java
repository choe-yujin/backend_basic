package com.metaverse.servlet.chap03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/exception") // value 명시해줘도, 안해줘도 됨
public class ExceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        try {
            String number = req.getParameter("number");
            if(number == null) {
                throw new IllegalArgumentException("숫자를 입력해주세요");
            }
            if(number.equals("0")) {
                throw new Exception("알 수 없는 오류");
            }

            int num = Integer.parseInt(number);
            out.println("<html> <body>");
            out.println("<h1> 입력한 숫자 " + num + "</h1>");
            out.println("</body> </html>");

        } catch(NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>오류 : 숫자 형식의 데이터가 아닙니다. </h1>");
            out.println("<a href='chap03/index.html'> 다시 시도하기 </a>");
        } catch(IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>오류 : 매개변수가 없습니다. </h1>");
            out.println("<a href='chap03/index.html'> 다시 시도하기 </a>");
        } catch(Exception e) {
            resp.sendError(500, "500번 에러 발생");
        }
    }
}

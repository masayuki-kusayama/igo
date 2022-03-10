package com.example.igobrowsegame;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("user");
        String pass = request.getParameter("password");
        try {
            if (manipulateUserDB.authentication(name, pass)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", name);
                RequestDispatcher dispatcher = request.getRequestDispatcher("MainMenu.jsp");
                dispatcher.forward(request, response);
            }
            request.setAttribute("message", "id,もしくはpasswordが間違っています");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "無効な入力です");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}

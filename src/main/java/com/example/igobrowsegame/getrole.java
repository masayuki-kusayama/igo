package com.example.igobrowsegame;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "getrole", value = "/getrole")
public class getrole extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String role = request.getParameter("role");
        String roomname = request.getParameter("roomname");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        ServletContext application = this.getServletContext();
        Map<String, player> playerlist = (Map<String, player>) application.getAttribute("playerlist");
        player p = playerlist.get(roomname);
        request.setAttribute("name", roomname);
        if (p.get(role) != null) {
            request.setAttribute("message", "既に他のユーザーがこの石のプレイヤーになっています");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/room.jsp");
            dispatcher.forward(request, response);
        } else if (p.getopponent(role) == username) {
            request.setAttribute("message", "あなたは既に他の石のプレイヤーです");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/room.jsp");
            dispatcher.forward(request, response);
        } else {
            p.set(role, username);
            request.setAttribute("message", "あなたが" + role + "のプレイヤーです");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/room.jsp");
            dispatcher.forward(request, response);
        }
    }
}

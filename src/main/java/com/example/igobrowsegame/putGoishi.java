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

@WebServlet(name = "putGoishi", value = "/putGoishi")
public class putGoishi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ap = this.getServletContext();
        request.setCharacterEncoding("UTF-8");
        String roomname = request.getParameter("roomname");
        request.setAttribute("name", roomname);
        Map<String, goban> map = (Map<String, goban>) ap.getAttribute("gobanlist");
        goban currentboard = map.get(roomname);
        Map<String, player> playerlist = (Map<String, player>) ap.getAttribute("playerlist");
        player p = playerlist.get(roomname);

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username.equals(p.get("shiro")) & !currentboard.currentturn()) {
            currentboard.put("shiro", Integer.parseInt(request.getParameter("place1")), Integer.parseInt(request.getParameter("place2")));
            RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
            dispatcher.forward(request, response);
        } else if (username.equals(p.get("kuro")) & currentboard.currentturn()) {
            currentboard.put("kuro", Integer.parseInt(request.getParameter("place1")), Integer.parseInt(request.getParameter("place2")));
            RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
            dispatcher.forward(request, response);
        }
    }
}

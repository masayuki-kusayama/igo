package com.example.igobrowsegame;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "resign", value = "/resign")
public class resign extends HttpServlet {
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
            request.setAttribute("message","白が投了しました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
            dispatcher.forward(request, response);
        } else if (username.equals(p.get("kuro")) & currentboard.currentturn()) {
            request.setAttribute("message","黒が投了しました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
            dispatcher.forward(request, response);
        }
    }
}
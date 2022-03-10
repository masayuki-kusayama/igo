package com.example.igobrowsegame;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CreateRoom", value = "/CreateRoom")
public class CreateRoom extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = this.getServletContext();
        List<room> roomlist = (List<room>) application.getAttribute("roomlist");
        if (roomlist == null) {
            roomlist = new ArrayList<>();
            application.setAttribute("roomlist", roomlist);
            roomlist = (List<room>) application.getAttribute("roomlist");
        }
        request.setCharacterEncoding("UTF-8");
        String roomname = request.getParameter("roomname");
        room room = new room(roomname);
        for (room temp : roomlist) {
            if (temp.getRoomName().equals(roomname)) {

                RequestDispatcher dispatcher = request.getRequestDispatcher("MainMenu.jsp");
                dispatcher.forward(request, response);
            }
        }
        ManipulateRoomLogic mrl = new ManipulateRoomLogic();
        mrl.create(room, roomlist);
        application.setAttribute("roomlist", roomlist);

        Map<String, goban> gobanlist = (Map<String, goban>) application.getAttribute("gobanlist");
        if (gobanlist == null) {
            gobanlist = new HashMap<String, goban>();
            application.setAttribute("gobanlist", gobanlist);
            gobanlist = (Map<String, goban>) application.getAttribute("gobanlist");
        }
        gobanlist.put(roomname, new goban());
        gobanlist.get(roomname).init();
        application.setAttribute("gobanlist", gobanlist);

        Map<String, player> playerlist = (Map<String, player>) application.getAttribute("playerlist");
        if (playerlist == null) {
            playerlist = new HashMap<String, player>();
            application.setAttribute("playerlist", playerlist);
            playerlist = (Map<String, player>) application.getAttribute("playerlist");
        }
        playerlist.put(roomname, new player());
        application.setAttribute("playerlist", playerlist);

        RequestDispatcher dispatcher = request.getRequestDispatcher("MainMenu.jsp");
        dispatcher.forward(request, response);
    }
}

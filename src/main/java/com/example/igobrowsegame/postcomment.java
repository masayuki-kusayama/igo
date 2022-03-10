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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "postcomment", value = "/postcomment")
public class postcomment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ap = this.getServletContext();
        Map<String, List<comment>> map = (Map<String, List<comment>>) ap.getAttribute("ComWithRoom");
        if (map == null) {
            ap.setAttribute("ComWithRoom", new HashMap<String, List<comment>>());
            map = (Map<String, List<comment>>) ap.getAttribute("ComWithRoom");
        }
        request.setCharacterEncoding("UTF-8");
        String roomname = request.getParameter("roomname");
        String text = request.getParameter("comment");
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        if (!map.containsKey(roomname)) {
            map.put(roomname, new ArrayList<>());
        }
        postcommentLogic pcl = new postcommentLogic();
        pcl.post(new comment(username, text), map.get(roomname));
        ap.setAttribute("ComWithRoom", map);
        request.setAttribute("name", roomname);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/room.jsp");
        dispatcher.forward(request, response);
    }
}

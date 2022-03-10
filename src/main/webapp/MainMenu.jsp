<%@ page import="com.example.igobrowsegame.room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<room> roomlist = (List<room>) application.getAttribute("roomlist");
    String name = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <title>MainMenu</title>
    <link href="MainMenu.css" rel="stylesheet">
</head>
<body>
<header>
    <nav>
        <ul class="main-nav">
            <li>
                <a href="MainMenu.jsp">メインメニュー</a>
            </li>
            <li>
                <a href="index.jsp">ログアウト</a>
            </li>
        </ul>
    </nav>
</header>
<h2><%= "ようこそ" + name + "さん！" %>
</h2>
<br/>
<form action="CreateRoom" method="post">
    <input type="text" name="roomname">
    <input type="submit" value="ルーム作成">
</form>
<div class="box">
    <% if (roomlist != null) {
        for (room room : roomlist) {%>
    <a id="room" href="room.jsp?name=<%=room.getRoomName()%>"><%=room.getRoomName()%>
    </a><br/>
    <%
            }
        }
    %>
</div>


</body>
</html>
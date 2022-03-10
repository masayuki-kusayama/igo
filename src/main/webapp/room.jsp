<%@ page import="com.example.igobrowsegame.comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.igobrowsegame.goban" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.example.igobrowsegame.player" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String name = request.getParameter("name");
    if(name != null){
        byte[] byteData = name.getBytes("ISO_8859_1");
        name = new String(byteData, StandardCharsets.UTF_8);
    }else{
        name = (String) request.getAttribute("name");
    }

    Map<String, List<comment>> map = (Map<String, List<comment>>) application.getAttribute("ComWithRoom");
    Map<String, goban> gomap = (Map<String, goban>) application.getAttribute("gobanlist");
    goban go = gomap.get(name);
    int[][] currentboard = go.get();
    String message = (String) request.getAttribute("message");

    Map<String, player> playerlist = (Map<String, player>) application.getAttribute("playerlist");
    player p = playerlist.get(name);
    String kuro = p.get("kuro");
    String shiro = p.get("shiro");
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="room.css" rel="stylesheet">
</head>
<body>
<header>
<h1>ルーム名：<%=name%>
</h1>
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
<%
    if (kuro != null) {
%><p><%="黒のプレイヤー："+kuro+"    現在のアゲハマ："+ go.getpoint("kuro")
%>
</p><%
    }%>
<%
    if (shiro != null) {
%><p><%="白のプレイヤー："+shiro+"   現在のアゲハマ（コミ6目半を含む）："+ go.getpoint("shiro")%>
</p><%
    }%>
<%
    if (message != null) {
%><p><%=message%></p><%
    }%>
<a href="room.jsp?name=<%=name%>">更新</a>
<div class="playercolor">
    <form action="getrole" method="post">
        <input type="hidden" name="roomname" value="<%=name%>">
        <input type="hidden" name="role" value="kuro">
        <button type="submit">黒をとる</button>
    </form>
    <form action="getrole" method="post">
        <input type="hidden" name="roomname" value="<%=name%>">
        <input type="hidden" name="role" value="shiro">
        <button type="submit" name="role" value="shiro">白をとる</button>
    </form>
    <form action="pass" method="post">
        <input type="hidden" name="roomname" value="<%=name%>">
        <button type="submit">パスをする</button>
    </form>
    <form action="resign" method="post">
        <input type="hidden" name="roomname" value="<%=name%>">
        <button type="submit">投了する</button>
    </form>
</div>

<form class = "postcom" action="postcomment" method="post">
    <input class="text" type="text" name="comment">
    <input type="hidden" name="roomname" value="<%=name%>">
    <input type="submit" value="投稿">
</form>

<div class="main">
    <canvas id="canvas" width="700" height="700">このブラウザはHTML5 canvasに対応していません</canvas>
    <div class="comment">
    <% if (map != null) {
        if (map.containsKey(name)) {
            for (comment temp : map.get(name)) {%>
    <p><%=temp.getname() + ":" + temp.getcomment()%>
    </p>
    <%
                }
            }
        }
    %>
    </div>
</div>
<script type="text/javascript">
    var currentboard = [];
    for (let x = 0; x < 13; x++) {
        currentboard[x] = [];
    }
    <%for(int i=0;i<13;i++){
    for(int j=0;j<13;j++){%>
    currentboard[<%=i%>][<%=j%>] =<%=currentboard[i][j]%>;
    <%}}%>
    var roomname = "<%=name%>"
</script>
<script type="text/javascript" src="gocanvas.js"></script>
</body>
</html>
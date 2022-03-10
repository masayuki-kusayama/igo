<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%String message = (String) request.getAttribute("message");%>
<!DOCTYPE html>
<html>
<head>
    <title>囲碁対戦ゲーム</title>
    <link href="index.css" rel="stylesheet">
</head>
<body>
<div class="home">
    <h1>シンプルなオンライン囲碁対戦ゲーム
    </h1>

    <form action="login" method="post">
        <label id="username">username:</label>
        <input type="text" name="user">
        <label id="password">password:</label>
        <input type="password" name="password">
        <input id="submit" type="submit" value="ログイン">
    </form>
    <%
        if (message != null) {
    %><p id="message"><%=message%>
</p><%
    }%>
    <a id="resister" href="resister.jsp">ユーザー登録</a>
</div>
</body>
</html>
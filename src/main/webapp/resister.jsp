<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserResister</title>
    <link href="resister.css" rel="stylesheet">
</head>
<body>
<div class="home">
    <h1>ユーザーネームとパスワードを登録してください</h1>
    <form action="UserResister" method="post">
        <label id="username">username:</label>
        <input type="text" name="user">
        <label id="password">password:</label>
        <input type="password" name="password">
        <input id="submit" type="submit" value="登録">
    </form>
</div>
</body>
</html>

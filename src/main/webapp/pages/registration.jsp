<%--
  Created by IntelliJ IDEA.
  User: Casper
  Date: 09.06.2018
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrtion</title>
    <link rel="stylesheet" type="text/css" href="/style/main.css">
</head>
<body>

<div id="content">
    <h1>You are in Registration Page!</h1>
    <br>
    <form action="/RegisterNewUser" method="post" class="registrationForm">
        <input type="email" name="username" placeholder="email">
        <input type="password" name="password" placeholder="password">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit">
    </form>
    <div id="mainpageLink">
        <div class="registrationLink"><a href="/">To main Page</a></div>
    </div>

</div>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Casper
  Date: 01.07.2018
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FailureLogIn</title>
</head>
<body>
    <div id="content">
        <h1>You are Fail logining!!!</h1>
        <br>
        <hr>

        <div id="registration">
        <form action="/logIn" method="post">
            <input type="email" name="username" placeholder="username">
            <input type="password" name="password" placeholder="password">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
            <input type="submit">
        </form>
        <div class="registrationLink"><a href="">Password recovery</a> or <a>Registration</a></div>

        </div>
    </div>
</body>
</html>

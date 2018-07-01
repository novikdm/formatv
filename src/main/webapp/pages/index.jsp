<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Casper
  Date: 09.06.2018
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FormaTV</title>
    <link rel="stylesheet" type="text/css" href="/style/main.css">
</head>
<body>
<div id="content">
    <h1>Hello! You are in Index!</h1>
    <hr>
    <sec:authorize access="isAuthenticated()">
        <h3>
            You are loginned as <sec:authentication property="principal.username" />
        </h3>
        <form action="/logout" method="post">
            <input type="submit" value="Logout">

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">

        </form>
    </sec:authorize>
    <hr>
    <sec:authorize access="isAnonymous()">
        <div id="registration">
            <form action="/logIn" method="post">
                <input type="email" name="username" placeholder="username">
                <input type="password" name="password" placeholder="password">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}">
                <input type="submit">
            </form>
            <div class="registrationLink"><a href="/login">Sign In</a></div>
            <div class="registrationLink"><a href="/Registration">Registration</a></div>
        </div>
    </sec:authorize>


    ----
    <sec:authorize access="isAuthenticated()">

    
        
    
        <h4>My User:</h4>

        email: <sec:authentication property="principal.username" />
        <br>
        name: <sec:authentication property="principal.customerDetails.name" />
        <br>
        id: <sec:authentication property="principal.authorities" />
        <br>
        Avatar:<img src="<sec:authentication property="principal.customerDetails.avatar"  />" width="100px">



    </sec:authorize>
    <hr>
    <div>
        <form action="/testing" method="post" enctype="multipart/form-data">
            <input type="file" name="ava" id="">
            <input type="submit">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
        <a href="/testing">Test</a></div>



</div>

</body>
</html>

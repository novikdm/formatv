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
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>FormaTV</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/script/script.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>
<body>
<header>
    <div class="row">
        <div class="logo">Logo</div>
        <div class="serch">
            <form>
                <div class="inputDiv">
                    <input type="text" name="serch-input" placeholder="Поиск">
                    <i class="fas fa-search"></i>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}">
                </div>
            </form>
        </div>
        <div class="relPos">
            <div class="userIcon">
                <sec:authorize access="isAnonymous()">
                <div class="avtorization-hide">
                        <a class="log-in-button" href="#">Вход</a>
                        <a class="log-up-button" href="#">Регистрация</a>
                </div>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <i class="fas fa-user"></i>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN' )">
                        <img src="<sec:authentication property="principal.customerDetails.avatar"  />" width="50px">
                        <sec:authentication property="principal.customerDetails.name" />
                    </sec:authorize>
                    <br>
                    <form action="/logout" method="post">
                        <input type="submit" value="Logout">
                        <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}">
                    </form>
                </sec:authorize>
            </div>
        </div>
    </div>
</header>
<div class="main-block">
    <div class="row">
        <div class="nav">
            <div class="nav-block">
                <h2 class="title-text">Фильмы</h2>
            </div>
            <div class="nav-block">
                <h2 class="title-text">Сериалы</h2>
            </div>
            <div class="nav-block">
                <h2 class="title-text">Люди</h2>
            </div>
            <div class="nav-block">
                <h2 class="title-text">Рейтенги</h2>
            </div>
        </div>
        <div class="sidebar">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur officiis ut rem minus. Odio quae quos velit assumenda voluptatem perspiciatis nihil quia fugit porro ullam sunt, dolorum dolore similique in!
        </div>
    </div>
</div>
<footer>
    <div class="row">
        <small>Copyright © <time datetime="2018">2018</time> FormaTV.com.ua</small>
    </div>
</footer>

<div class="modal in">
    <div class="log-in">
        <i class="exit fas fa-times exit-icon"></i>
        <div class="userIcon">
            <i class="fas fa-user"></i>
        </div>
        <form action="/logIn" method="post">
            <div class="inputDiv">
                <input type="text" name="username" placeholder="Email">
                <i class="far fa-envelope"></i>
            </div>
            <div class="inputDiv">
                <input type="password" name="password" placeholder="Password">
                <i class="fas fa-lock"></i>
            </div>
            <p>
                <input type="submit" value="Log in">
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </div>
</div>
<div class="modal up">
    <div class="log-up">
        <i class="exit fas fa-times exit-icon"></i>
        <div class="userIcon">
            <i class="fas fa-user"></i>
        </div>
        <form action="/RegisterNewUser" method="post" class="registrationForm" enctype="multipart/form-data">
            <div class="inputDiv">
                <input type="text" name="username" placeholder="Email">
                <i class="far fa-envelope"></i>
            </div>
            <div class="inputDiv">
                <input type="password" name="password" placeholder="Password">
                <i class="fas fa-lock"></i>
            </div>
            <%--<div class="inputDiv">--%>
                <%--<input type="password" name="password-repeat-input" placeholder="repeat Password">--%>
                <%--<i class="fas fa-lock"></i>--%>
            <%--</div>--%>
            <div class="inputDiv">
                <input type="text" name="name" placeholder="Name">
                <i class="far fa-envelope"></i>
            </div>
            <div class="inputDiv">
                <input type="date" name="birthday" placeholder="Name">
                <i class="fas fa-birthday-cake"></i>
            </div>
            <div class="inputDiv">
                <!-- 			inputDivLabel	    <label class="file_upload">
                                        <span class="button">Выбрать</span>
                                        <mark>Файл не выбран</mark>
                                        <input type="file" accept="image/*,image/jpeg,image/png">
                                    </label> -->
                <div class="file_upload">
                    <%--<button type="button">Выбрать</button>--%>
                    <%--<div>Файл не выбран</div>--%>
                    <input type="file" name="avatarpic" accept="image/*,image/jpeg,image/png">
                </div>
                <i class="fas fa-user-circle"></i>
            </div>

            <p>
                <input type="submit" value="Log up">
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">

        </form>
    </div>
</div>

</body>
</html>
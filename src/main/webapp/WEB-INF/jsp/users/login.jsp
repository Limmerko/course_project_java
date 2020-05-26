<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/format-text.css">
    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <spring:url value="resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />

    <title>Авторизация</title>

    <ul class="menu-main">
        <li class="nazvanie"><a href="/">KonohaLIVE</a></li>
        <li><a href="/problems">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Сообщить о проблеме <i class="far fa-bell"></i></button>
        </form></li>
        <li>
            <div>
                <span class="format-text-username" style="color: #606060;"><h4>${pageContext.request.userPrincipal.name}</h4></span>
                <sec:authorize access="!isAuthenticated()">
                <h4><a href="/login" class="current">Войти</a></h4>
        </li>
        <li>
            <h4><a href="/registration">Зарегистрироваться</a></h4>
            </sec:authorize>
        </li><li>
            <sec:authorize access="isAuthenticated()">
                <h4><a href="/logout">Выйти</a></h4>
            </sec:authorize>
            </div>
        </li>
    </ul>

</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>

<div class="form-group" align="center">
    <div class="col-sm-3">
    <form method="post" action="/login">
        <span class="format-text" align="center" style="color: #606060;"><h4>Вход в систему</h4></span>
        <div>
            <input class="form-control" name="username" type="text" placeholder="Введите логин" width="400px" autofocus="true"/>
            <br>
            <input class="form-control" name="password" type="password" placeholder="Введите пароль" width="400px"/>
            <br>
            <div class="col-auto">
            <button type="submit" class="btn btn-primary">Войти</button>
            </div>
            <br>
            <label class="format-text-label" style="color: #606060;">Нет аккаунта? <a href="/registration">Зарегистрируйтесь</a></label>
        </div>
    </form>
    </div>
</div>

</body>
</html>
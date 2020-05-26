<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

    <title>Зарегистрироваться</title>


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
                <h4><a href="/login">Войти</a></h4>
        </li>
        <li>
            <h4><a href="/registration" class="current">Зарегистрироваться</a></h4>
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
<div class="form-group" align="center">
    <div class="col-sm-3">
    <form:form method="post" modelAttribute="userForm">
        <span class="format-text" align="center" style="color: #606060;"><h4>Регистрация</h4></span>
        <div>
            <form:input class="form-control" type="text" path="login" placeholder="Введите логин"
                        autofocus="true"></form:input>
            <form:errors path="login"></form:errors>
                ${usernameError}
        </div>
        <br>
        <div>
            <form:input class="form-control" type="password" path="password" placeholder="Введите пароль"></form:input>
        </div>
        <br>
        <div>
            <form:input class="form-control" type="password" path="passwordConfirm"
                        placeholder="Повторите пароль"></form:input>
            <form:errors path="password"></form:errors>
                ${passwordError}
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form:form>
    <a href="/login">У меня уже есть аккаунт</a>
    </div>
</div>
</body>
</html>
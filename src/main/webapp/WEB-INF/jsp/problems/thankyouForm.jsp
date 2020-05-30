<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/format-text.css">

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <title>Проблемы</title>


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
                <h4><a href="/login">Вход <i class="fas fa-user"></i></a></h4>
            </div>
        </li>
        <li>
            <div>
                <h4><a href="/registration">Регистрация <i class="fas fa-user-plus"></i></a></h4>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <h4><a href="/logout">Выход <i class="fas fa-user-slash"></i></a></h4>
                </sec:authorize>
        </li>
        <li>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
            <h4><a href="/admin/problems">Администрирование <i class="fas fa-crown"></i></a></h4>
        </li>
        <li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <h4><a href="/admin">Пользователи <i class="fas fa-users"></i></a></h4>
            </sec:authorize>
            </sec:authorize>
            </div>
        </li>
    </ul>
</head>
<body>


<div class="ThxForm">Спасибо,
    <br> ваша заявка была отправлена!
    <p> Наши модераторы ее проверят и скоро она появится на сайте.
</div>


</body>
</html>
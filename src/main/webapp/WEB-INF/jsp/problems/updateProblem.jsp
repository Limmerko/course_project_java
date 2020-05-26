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
    <title>Проблемы</title>

    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="/">Главная</a></li>
        <li><a href="/problems" class="current">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Изменить проблему <i class="far fa-bell"></i></button>
        </form></li>
        <li>
            <div>
                <span class="format-text-username" style="color: #606060;"><h4>${pageContext.request.userPrincipal.name}</h4></span>
                <sec:authorize access="!isAuthenticated()">
                <h4><a href="/login">Войти</a></h4>
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
<form:form method="post" modelAttribute="problem" enctype="multipart/form-data">
<div>
    <table class="reportProblems" align="center">
        <tr>
            <td align="center" style="font-size: 24px">
                <b>Изменить проблему</b>
            </td>
        </tr>
        <form:hidden path="id" value="${problem.id}"/>
        <tr>
            <td align="center">
                <div id="map" style="width: 90%; height: 300px; margin: 10px auto"></div>
                <input type="text" id="myInput" value="${problem.address}"/>
                <form:hidden path="address" name="myInputBD" id="myInputBD" value="${problem.address}"/>
            </td>
        </tr>
        <tr>
           <td align="center">
                <form:select path="category" class="categories">
                    <form:option value="${problem.category}">${problem.category.getDescription()}</form:option>>
                    <c:forEach var="categ" items="${categories}">
                        <form:option value="${categ}">${categ.getDescription()}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td align="center">
                <form:input class="form-control" rows="3"  path="description" value="${problem.description}" type="textarea" placeholder="Описание проблемы" style="height: 100px"/>
            </td>
        </tr>
        <tr>
            <td align="center">
                <form:select path="status" class="categories">
                    <form:option value="${problem.status}">${problem.status.getDescription()}</form:option>
                    <c:forEach var="status" items="${statuses}">
                        <form:option value="${status}">${status.getDescription()}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
        </tr>
        <tr>
            <td>
                <button class="glo" type="submit" >Отправить</button>
            </td>
        </tr>
    </table>
</div>
</form:form>
</html>
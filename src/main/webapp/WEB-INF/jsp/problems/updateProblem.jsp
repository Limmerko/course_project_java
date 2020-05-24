<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="../../css/main-menu.css">
    <link rel="stylesheet" href="../../css/table-style.css">
    <link rel="stylesheet" href="../../css/buttonReportProblem.css">
    <title>Проблемы</title>

    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="/">Главная</a></li>
        <li><a href="/problems">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Сообщить о проблеме <i class="fas fa-info"></i></button>
        </form></li>
    </ul>


</head>
<body>
<form:form method="post" modelAttribute="problem" enctype="multipart/form-data">
<div>
    <table class="reportProblems" align="center">
        <tr>
            <td align="center" style="font-size: 24px">
                <b>Сообщить о проблеме</b>
            </td>
        </tr>
        <tr>
            <td align="center">
                <div id="map" style="width: 90%; height: 300px; margin: 10px auto"></div>
                <input type="text" id="myInput" value="${problem.address} />
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
                <button class="glo" type="submit" onlclick="ZdesDoljenBitRedirect()">Отправить</button>
            </td>
        </tr>
    </table>
</div>
</form:form>
</html>
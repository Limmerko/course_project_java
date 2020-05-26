<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <spring:url value="resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />
    <title>Admin</title>
</head>

<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<div>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <th>ID</th>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Роль</th>
        <th></th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                        <%--текущие роли--%>
                        <c:forEach items="${user.roles}" var="role">${role.name}</c:forEach></option>
                        <form:select path="roles">
                            <option value="">Выберите роль:</option>
                            <c:forEach var="role" items="${roles}">
                                <form:option value="${role}">${role}</form:option>
                            </c:forEach>
                            <button type="submit">Изменить</button>
                        </form:select>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/delete" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action"  value="delete"/>
                        <button type="submit" class="btn btn-danger"><i class="fas fa-user-minus"></i> Удалить</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
    <a href="/admin/problems">Проблемы</a>
</div>
</body>
</html>
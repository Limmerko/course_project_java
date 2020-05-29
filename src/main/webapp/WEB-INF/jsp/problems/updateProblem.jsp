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
    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <title>Проблемы</title>

    <ul class="menu-main">
        <li class="nazvanie"><a href="/">KonohaLIVE</a></li>
        <li><a href="/problems" class="current">Проблемы</a></li>
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
                <h4><a href="/admin/problems" class="current">Администрирование <i class="fas fa-crown"></i></a></h4>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <h4><a href="/admin">Пользователи <i class="fas fa-users"></i></a></h4>
                </sec:authorize>
            </sec:authorize>
            </div>
        </li>
    </ul>


</head>
<body>

<form>
<input hidden type="text" name="problemInputEdit" value="${problem.address}"/>
<input hidden type="text" name="problemInputAddressEdit" value="${problem.address}"/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/problems-edit.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


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
                <div id="map" style="width: 90%; height: 300px; margin: 10px auto; border: 3px solid #bfbfbf"></div>
                <input class="form-control" type="text" id="problemInput" value="${problem.address}" readonly/>
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
            <td align="center">
                <c:forEach var="photo" items="${problem.photos}">
                    <div>
                        <img src="${photo.url}" class="rounded" width="150px" height="auto">
                        <form action="/admin/problems/edit/photo/main" method="post" style="display: inline-block;" data-toggle="tooltip" data-placement="bottom" title="Сделать главным">
                            <input hidden value="${photo.id}" name="photoId">
                            <input hidden value="${problem.id}" name="problemId">
                            <button type="submit" class="btn btn-primary"><i class="fas fa-image"></i></button>
                        </form>
                        <form action="/admin/problems/edit/photo/delete" method="post" style="display: inline-block;" data-toggle="tooltip" data-placement="bottom" title="Удалить фото">
                            <input hidden value="${photo.id}" name="photoId">
                            <input hidden value="${problem.id}" name="problemId">
                            <button type="submit" class="btn btn-danger"><i class="fas fa-times"></i></button>
                        </form>
                    </div>
                </c:forEach>
            </td>
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
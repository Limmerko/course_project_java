<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main-menu.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/format-text.css">

    <title>Главная</title>

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>



    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="/" class="current">Главная</a></li>
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
                </div>
        </li>
        <li>
            <div>
            <h4><a href="/registration">Зарегистрироваться</a></h4>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <h4><a href="/logout">Выйти</a></h4>
            </sec:authorize>
            </div>
        </li>
    </ul>

</head>
<body>

<c:forEach items="${problems}" var="problem">
    <input hidden type="text" name="problemsCoords" value="${problem.address}"/>
    <input hidden type="text" name="problemsId" value="${problem.id}"/>
</c:forEach>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ya-map.js"></script>



<form>
<table>
<div class="form-group">
    <div class="row-cols-sm-4">
        <tr><td>
    <select id="category" name="category" class="categories">
        <option value="">Укажите категорию проблемы</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category}">
                <c:out value="${category.description}"></c:out>
            </option>
        </c:forEach>
    </select>
        </td><td>
       <button class="btn btn-primary" type="submit" style="height: 50px">Фильтр</button>
    </div>
    </td>
    <td>
    <label>${str}</label>
    </td></tr>
</div>
</table>
</form>


<p>
<div id="map" style="width: 90%; height: 600px;  margin: 0 auto"></div>

</body>
</html>
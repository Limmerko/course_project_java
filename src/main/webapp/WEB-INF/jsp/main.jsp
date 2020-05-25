<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main-menu.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">

    <title>Главная</title>

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ya-map.js"></script>


    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="/" class="current">Главная</a></li>
        <li><a href="/problems">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Сообщить о проблеме <i class="far fa-bell"></i></i></button>
        </form></li>
    </ul>

</head>
<body>

<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="/login">Войти</a></h4>
        <h4><a href="/registration">Зарегистрироваться</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Выйти</a></h4>
    </sec:authorize>
</div>
<form>
    <select id="category" class="categories" style="margin: 0px 93px 0px 93px;">
        <option value="">Укажите категорию проблемы:</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category}">
                <c:out value="${category.description}"></c:out>
            </option>
        </c:forEach>
    </select>
    <button class="glo" type="submit">Фильтр</button>
</form>




<p>


<div id="map" style="width: 90%; height: 800px;  margin: 0 auto"></div>

</body>
</html>
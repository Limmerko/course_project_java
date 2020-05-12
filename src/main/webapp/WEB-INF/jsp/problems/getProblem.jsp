<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="css/main-menu.css">
    <link rel="stylesheet" href="css/table-style.css">
    <link rel="stylesheet" href="css/buttonReportProblem.css">
    <title>Новости</title>

    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="">Главная</a></li>
        <li><a href="" class="current" >Проблемы</a></li>
        <li><a href="">Новости</a></li>
        <li><form action="reportProblem.html">
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>


</head>
<body>
<h1>Problem info</h1>
    <c:forEach var="photo" items="${problem.photos}">
        <img src="${photo.url}">
    </c:forEach>
</body>
</html>
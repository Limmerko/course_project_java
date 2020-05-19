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
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>



</head>
<body>


<h1>Спасибо, ваша заявка была отправлена</h1>


</body>
</html>
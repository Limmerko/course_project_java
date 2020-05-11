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
        <li><a href="">Проблемы</a></li>
        <li><a href="" class="current">Новости</a></li>
        <li><form action="reportProblem.html">
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>


</head>
<body>
<form:form method="post" action="/news/new" modelAttribute="news">
    <table class="reportProblems" align="center">
        <tr>
            <td align="center" style="font-size: 24px">
                <b>Создать новость</b>
            </td>
        </tr>
        <tr>
            <td align="center">
                <form:input path="title" type="commet" placeholder="Заголовок" style="height: 100px"/>
            </td>
        </tr>
        <tr>
            <td align="center">
                <form:input path="description" type="commet" placeholder="Описание" style="height: 100px"/>
            </td>
        </tr>
        <tr>
            <td align="center" style="height: 50px;">
                <form:input path="photos" type="file"/>
            </td>
        </tr>
        <tr>
            <td>
                <button class="glo" type="submit" >Отправить</button>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
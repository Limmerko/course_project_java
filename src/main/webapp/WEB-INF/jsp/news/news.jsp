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

<table class="news">
    <c:forEach var="oneNews" items="${news}">
        <TR>
            <thead>
            <TD>${oneNews.title}</TD>
            </thead>
        </TR>
        <tbody>
        <TR>
            <TD>${oneNews.description}</TD>
            <TD ROWSPAN=4 align="center"><img src="image/news1.jpg"></TD>
        </TR>
        </tbody>
        <tfoot>
        <TR>
            <TD>DATE</TD>
            <TD align="right">15 <img src="image/like_icon.png" class="semi"> 2 <img src="image/comment_icon.png" class="semi"></TD>
        </TR>
        </tfoot>
    </c:forEach>
</table>



</body>
</html>
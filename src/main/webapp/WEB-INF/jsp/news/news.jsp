<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" href="../../../css/main-menu.css">
    <link rel="stylesheet" href="../../../css/table-style.css">
    <link rel="stylesheet" href="../../../css/buttonReportProblem.css">
    <spring:url value="resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />
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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<h1>News</h1>

<div class="container px-lg-5">
    <div class="row mx-lg-n5">
        <c:forEach var="oneNews" items="${news}">
            <div class="col">
                <table class="table">
                    <tbody>
                        <tr scope="row">
                            <td>${oneNews.title}</td>
                            <td rowspan="3">
                                <img src="${oneNews.mainPhoto}" width="150px" height="150px">
                            </td>
                        </tr>
                        <tr scope="row">
                            <td>${oneNews.description}</td>
                        </tr>
                    </tbody>
                    <tfoot>
                    <tr scope="row">
                        <td>13 мая 2020</td>
                        <td align="right">15 <img src="image/like_icon.png" class="semi"> 2</td>
                    </tr>
                    </tfoot>
                </table>
            </div>
            <br/>
        </c:forEach>
    </div>
</div>


</body>
</html>
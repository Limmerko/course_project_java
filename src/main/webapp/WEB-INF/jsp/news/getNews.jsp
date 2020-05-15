<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" href="../css/main-menu.css">
    <link rel="stylesheet" href="../css/table-style.css">
    <link rel="stylesheet" href="../css/buttonReportProblem.css">

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

<h1>Информация о новости</h1>

<div class="container px-lg-5">
    <div class="row mx-lg-n5">-
            <div class="col">
                        <table class="table table-striped table-bordered" >
                            <tbody>
                            <tr scope="row">
                                <td scope="col">
                                    <!--<img src="${news.mainPhoto}" width="250px" height="250px"> -->
                                    <div id="carouselPhotos" class="carousel slide" data-ride="carousel" >
                                        <div class="carousel-inner">
                                            <div class="carousel-item active">
                                                <cl:img src="${news.mainPhoto}" class="d-block w-100" alt="..."width="100" height="150" crop="fill">
                                            </div>
                                            <с:forEach var="photo" items="${news.photos}">
                                                <div class="carousel-item">
                                                    <img src="${photo.url}" class="d-block w-100" alt="..." width="200px" height="auto">
                                                </div>
                                            </с:forEach>
                                        </div>
                                        <a class="carousel-control-prev" href="#carouselPhotos" role="button" data-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="carousel-control-next" href="#carouselPhotos" role="button" data-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                    </div>
                                </td>
                                <td rowspan="3" border="1">
                                    КОММЕНТАРИИ
                                </td>
                            </tr>
                            <tr scope="row">
                                <td>${news.title}</td>
                            </tr>
                            <tr scope="row">
                                <td>${news.description}</td>
                            </tr>
                            <tr scope="row">
                                <td>${news.creationDate}</td>
                                <td><input type="text" placeholder="Оставить комментарий"/></td>
                            </tr>
                            </tbody>
                        </table>
            </div>
            <br/>
    </div>
</div>





</body>
</html>
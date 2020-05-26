<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/carousel-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/format-text.css">

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <spring:url value="resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />

    <title>Проблемы</title>

    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="/">Главная</a></li>
        <li><a href="/problems" class="current">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Сообщить о проблеме <i class="far fa-bell"></i></button>
        </form></li>
    </ul>


</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<span class="format-text" align="center" style="color: #606060;"><h3>Информация о проблеме</h3></span>

<div class="container px-lg-5">
    <div class="row mx-lg-n5">
        <div class="col">
            <table class="table table-striped table-bordered" >
                <tbody>
                <tr scope="row">
                    <td scope="col">
                        <div id="carouselPhotos" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner" style="width: 500px; height: 300px;">
                                <div class="carousel-item active">
                                    <img src="${problem.mainPhoto}" class="car-photo w-auto h-100" alt="..." style="width: 400px; height: auto; margin: auto; position: center;">
                                </div>
                                <с:forEach var="photo" items="${problem.photos}">
                                    <div class="carousel-item">
                                        <img src="${photo.url}" class="car-photo w-auto h-100" alt="..." style="width: 400px; height: auto; margin: auto; position: center;">
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
                        <div style="max-height: 300px; overflow-y: auto">
                            <c:forEach var="comment" items="${problem.comments}">
                                <span class="badge badge-pill badge-primary">${comment.author.login}</span>
                                <p>${comment.text}</p>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
                <tr scope="row">
                    <td>${problem.description}</td>
                </tr>
                <tr scope="row">
                    <td>${problem.status.description}</td>
                </tr>
                <tr scope="row">
                    <td>${problem.category.description}</td>
                </tr>
                <tr scope="row">
                    <td>
                        <fmt:parseDate value="${ problem.creationDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
                    </td>
                    <td>
                        <form:form method="post" action="comments/new" modelAttribute="newComment" enctype="multipart/form-data">
                            <form:input hidden="true" class="form-control" id="problem" path="problem" type="text" value="${problem.id}"/>
                            <div class="input-group">
                                <form:input class="form-control" id="comment" path="text" type="text" placeholder="Оставить комментарий"/>
                                <button class="btn btn-outline-primary" type="submit" title="Оставить комментарий">
                                    <i class="far fa-paper-plane"></i>
                                </button>
                            </div>
                        </form:form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <br/>
    </div>
</div>
</body>
</html>
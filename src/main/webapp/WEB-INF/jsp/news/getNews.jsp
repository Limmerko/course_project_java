<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

    <title>Новости</title>

    <ul class="menu-main">
        <li class="nazvanie"><a href="/">KonohaLIVE</a></li>
        <li><a href="/problems">Проблемы</a></li>
        <li><a href="/news" class="current">Новости</a></li>
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
            <h4><a href="/admin/problems">Администрирование <i class="fas fa-crown"></i></a></h4>
        </li>
        <li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <h4><a href="/admin">Пользователи <i class="fas fa-users"></i></a></h4>
            </sec:authorize>
            </sec:authorize>
            </div>
        </li>
    </ul>


</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<div class="format-text" align="center" style="color: #606060;"><h3>Информация о новости</h3></div>

<div class="container px-lg-5">
    <div class="row mx-lg-n5">
            <div class="col">
                        <table class="table table-striped table-bordered" style="width: 800px; margin: auto">
                            <tbody>
                            <tr scope="row">
                                <td scope="col" style="width: 500px">
                                    <div id="carouselPhotos" class="carousel slide" data-ride="carousel" >
                                        <div class="carousel-inner text-center" style="width: 500px; height: 300px;">
                                            <div class="carousel-item active text-center" style="align-content: center;">
                                                <img src="${news.mainPhoto}" class="center-block w-auto h-100" alt="..." style="width: 100%; height: auto;">
                                            </div>
                                            <с:forEach var="photo" items="${news.photos}">
                                                <div class="carousel-item">
                                                    <img src="${photo.url}" class="center-block w-auto h-100" alt="..." style="width: 100%; height: auto;">
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
                                <td rowspan="3" border="1" style="width: 300px">
                                    <div style="max-height: 600px; overflow-y: auto">
                                        <c:forEach var="comment" items="${news.comments}">
                                            <span class="badge badge-pill badge-primary">${comment.author.login}</span>
                                            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
                                                <form action="/news/deleteComment/${news.id}">
                                                    <input hidden id="commentId" name="commentId" value="${comment.id}">
                                                    <button class="btn btn-outline-primary">Удалить</button>
                                                </form>
                                            </sec:authorize>
                                            <p>${comment.text}</p>
                                        </c:forEach>
                                    </div>
                                </td>
                            </tr>
                            <tr scope="row">
                                <td>${news.title}</td>
                            </tr>
                            <tr scope="row">
                                <td>${news.description}</td>
                            </tr>
                            <tr scope="row">
                                <%--<fmt:formatDate value="${news.creationDate}" pattern="MM/dd/yyyy"/>--%>
                                <td>
                                    <fmt:parseDate value="${ news.creationDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />

                                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_NEWS_MODERATOR')">
                                        <div style="display: inline-block; float: right;">
                                            <form action="/news/edit/${news.id}" method="get">
                                                <button class="btn btn-primary" type="submit"><i class="fas fa-pen"></i> Изменить</button>
                                            </form>
                                        </div>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="isAuthenticated()">
                                        <form:form method="post" action="comments/new" modelAttribute="newComment" enctype="multipart/form-data">
                                            <form:input hidden="true" class="form-control" id="news" path="news" type="text" value="${news.id}"/>
                                            <input hidden id="authorLogin" name="authorLogin" value="${pageContext.request.userPrincipal.name}"/>
                                            <div class="input-group">
                                            <form:input class="form-control" id="comment" path="text" maxlength="100" type="text" placeholder="Оставить комментарий"/>
                                                <button class="btn btn-outline-primary" type="submit" title="Оставить комментарий">
                                                    <i class="far fa-paper-plane"></i>
                                                </button>
                                            </div>
                                        </form:form>
                                    </sec:authorize>
                                    <sec:authorize access="!isAuthenticated()">
                                        <div class="input-group">
                                            <input class="form-control" type="text" placeholder="Авторизуйтесь"/>
                                            <button class="btn btn-outline-primary" disabled type="submit" title="Оставить комментарий">
                                                <i class="far fa-paper-plane"></i>
                                            </button>
                                        </div>
                                    </sec:authorize>
                                </td>
                            </tr>
                            </tbody>
                        </table>
            </div>
            <br/>
    </div>
</div>
</body>
<footer class="footerForm">
    <jsp:include page="${pageContext.request.contextPath}\..\footer.jsp"/>
</footer>
</html>
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

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/upvote.js"></script>

    <spring:url value="resources/css/bootstrap.css" var="bootstrap"/>
    <spring:url value="/resources/css/modern-business.css" var="startertemplate"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <link href="${startertemplate}" rel="stylesheet" />

    <title>Проблемы</title>

    <ul class="menu-main">
        <li class="nazvanie"><a href="/">KonohaLIVE</a></li>
        <li><a href="/problems" class="current">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Сообщить о проблеме <i class="far fa-bell"></i></button>
        </form></li>
        <li>
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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<span class="format-text" align="center" style="color: #606060;"><h3>Информация о проблеме</h3></span>

<div class="container px-xl-2">
    <div class="row mx-lg-n5">
        <div class="col">
            <table class="table table-striped table-bordered" style="width: 950px; margin: auto">
                <tbody>
                <tr scope="row">
                    <td scope="col" style="width: 500px">
                        <div id="carouselPhotos" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner text-center" style="width: 500px; height: 300px; text-align: center">
                                <div class="carousel-item active text-center" style="align-content: center;">
                                    <img src="${problem.mainPhoto}" class="img-fluid w-auto h-100" alt="..." style="width: 400px; height: auto;">
                                </div>
                                <с:forEach var="photo" items="${problem.photos}">
                                    <div class="carousel-item text-center">
                                        <img src="${photo.url}" class="img-fluid w-auto h-100" alt="..." style="width: 400px; height: auto;">
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
                    <td rowspan="4" border="1" style="width: 450px; max-height: 540px;">
                        <div style="height: 450px; max-height: 530px; overflow-y: auto">
                            <c:forEach var="comment" items="${problem.comments}">
                                <span class="badge badge-pill badge-primary">${comment.author.login}</span>
                                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
                                    <div style="display: inline-block; float: right;">
                                    <form action="/problems/deleteComment/${problem.id}">
                                        <input hidden id="commentId" name="commentId" value="${comment.id}">
                                            <button class="btn btn-outline-danger"><i class="fas fa-times"></i></button>
                                    </form>
                                    </div>
                                </sec:authorize>
                                <p>${comment.text}</p>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
                <tr scope="row">
                    <td>
                        <div lang="ru" style="word-break: break-all; hyphens: auto; word-wrap: break-word;">${problem.description} </div>
                    </td>
                </tr>
                <tr scope="row">
                    <td>
                        ${problem.category.description}
                    </td>
                </tr>
                <tr scope="row">
                    <td>${problem.status.description}</td>
                </tr>
                <tr scope="row">
                    <td>
                        <fmt:parseDate value="${problem.creationDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
                        <div style="display: inline-block;  float: right;">
                            <p id="countOfVOtes" style="display: inline-block; margin-right: 10px">${problem.countOfVotes}</p>
                            <sec:authorize access="isAuthenticated()">
                                <button id="upvoteButton"
                                        class="btn btn-outline-primary"
                                        onclick="upvoteProblem(${problem.id}, '${pageContext.request.userPrincipal.name}')"
                                        data-toggle="tooltip" data-placement="bottom" title="Поднять актуальность проблемы">
                                    <i class="far fa-arrow-alt-circle-up"></i>
                                </button>
                            </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                                <button id="upvoteButton" disabled
                                        class="btn btn-outline-primary">
                                    <i class="far fa-arrow-alt-circle-up"></i>
                                </button>
                            </sec:authorize>
                        </div>
                    </td>
                    <td>
                        <sec:authorize access="isAuthenticated()">
                            <form:form method="post" action="comments/new" modelAttribute="newComment" enctype="multipart/form-data">
                                <form:input hidden="true" class="form-control" id="problem" path="problem" type="text" value="${problem.id}"/>
                                <input hidden id="authorLogin" name="authorLogin" value="${pageContext.request.userPrincipal.name}"/>
                                <div class="input-group">
                                    <form:input class="form-control" id="comment" path="text" type="text" maxlength="100" placeholder="Оставить комментарий"/>
                                    <button class="btn btn-outline-primary" type="submit" title="Отправить">
                                        <i class="far fa-paper-plane"></i>
                                    </button>
                                </div>
                            </form:form>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <div class="input-group">
                                <input class="form-control" type="text" disabled placeholder="Авторизуйтесь"/>
                                <button class="btn btn-outline-primary" disabled type="submit" title="Отправить">
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
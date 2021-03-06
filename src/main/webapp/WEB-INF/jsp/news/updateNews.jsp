<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/format-text.css">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/word-limit.js"></script>

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


    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    <title>Изменить новость</title>
</head>
<body>

<span class="format-text" align="center" style="color: #606060;"><h3>Изменить новость</h3></span>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<div class="container">
    <div class="row">
        <div class="col-sm">

            <form:form method="post" action="/news/edit/{id}" modelAttribute="news" enctype="multipart/form-data">
                <form:hidden path="id" value="${news.id}"/>
                <div class="form-group">
                    <label for="title">Заголовок</label>
                    <form:input class="form-control" id="title" path="title" maxlength="100" type="text" value="${news.title}" placeholder="Заголовок"/>
                </div>

                <div class="form-group">
                    <label for="description">Описание</label>
                    <form:textarea class="form-control" rows="3" onkeyup="WordLimit()" name="descText"
                                   id="descText" path="description" type="textarea" placeholder="Описание проблемы"
                                   cssStyle="width: 90%; resize: none;"/>
                    <div class="counter">Осталось символов: <span id="wordCounter">255</span></div>
                </div>

                <div class="form-group">
                    <c:forEach var="photo" items="${news.photos}">
                        <div>
                            <img src="${photo.url}" class="rounded" width="150px" height="auto">
                            <form action="/admin/news/edit/photo/main" method="post" style="display: inline-block;">
                                <input hidden value="${photo.id}" name="photoId">
                                <input hidden value="${news.id}" name="newsId">
                                <button type="submit" class="btn btn-primary"><i class="fas fa-image"></i></button>
                            </form>
                            <form action="/admin/news/edit/photo/delete" method="post" style="display: inline-block;">
                                <input hidden value="${photo.id}" name="photoId">
                                <input hidden value="${news.id}" name="newsId">
                                <button type="submit" class="btn btn-danger"><i class="fas fa-times"></i></button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
                <!-- PHOTOS -->
                <button class="glo" data-toggle="tooltip"
                        data-placement="top" title="Изменить новость" type="submit">Отправить</button>
            </form:form>
        </div>
    </div>
</div>
</body>
<footer class="footerForm">
    <jsp:include page="${pageContext.request.contextPath}\..\footer.jsp"/>
</footer>
</html>
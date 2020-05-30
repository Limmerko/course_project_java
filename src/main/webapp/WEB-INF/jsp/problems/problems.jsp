<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>
    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/format-text.css">

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


<div style="text-align: center">
<sec:authorize access="isAuthenticated()">
    <form method="post" action="/problems">
        <input hidden id="authorLogin" name="authorLogin" value="${pageContext.request.userPrincipal.name}"/>
        <button class="btn btn-primary" type="submit" style="height: 50px;">Мои проблемы</button>
    </form>
</sec:authorize>
</div>

<form action="/problems">
    <table style="margin: auto">
        <div class="form-group">
            <div class="row-cols-sm-4">
                <tr><td>
                    <select id="category" name="category" class="categories">
                        <option value="">Укажите категорию проблемы</option>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category}">
                                <c:out value="${category.description}"></c:out>
                            </option>
                        </c:forEach>
                    </select>
                    <select id="votesFilter" name="votesFilter" class="categories">
                        <option value="false">
                            По дате
                        </option>
                        <option value="true">
                            По кол-ву оцениваний
                        </option>
                    </select>
                    <div style="margin: auto">${str}</div>
                </td><td>
                    <button class="btn btn-primary" type="submit" style="height: 50px">Фильтр</button>
            </div>
            </td></tr>
        </div>
    </table>
</form>

<div class="container px-lg-5">
    <div class="row mx-lg-n3">
        <c:forEach var="problem" items="${problems}">
            <div class="col-6 col-md-4">
                <form:form method = "GET" action = "/problems/${problem.id}">
                    <button type="submit" style="border-radius: 10px 10px; border: #007bff; box-shadow: 0 0 5px #007bff;">
                        <table class="table" style="height: 250px; width: 320px">
                            <tbody>
                                <tr scope="row">
                                    <td height="150px" colspan="2">
                                        <div style="text-align: center;">
                                            <img src="${problem.mainPhoto}" class="rounded"  width="auto" height="150px">
                                        </div>
                                    </td>
                                </tr>
                                <tr scope="row">
                                    <td colspan="2" style="height: 80px">
                                        <label hidden name="problemsCoords">${problem.address}</label>
                                        <label name="problemsAddress"></label>
                                    </td>
                                </tr>
                                <tr  scope="row">
                                    <td height="25px">
                                        <label name="problemsCategory">${problem.category.description}</label>
                                    </td>
                                    <td height="25px" align="right">
                                        <c:if test="${problem.status == 'RESOLVED'}">
                                            <span class="badge badge-pill badge-success" style="font-size: 15px">${problem.status.description}</span>
                                        </c:if>
                                        <c:if test="${problem.status == 'NOT_RESOLVED'}">
                                            <span class="badge badge-pill badge-warning" style="font-size: 15px">${problem.status.description}</span>
                                        </c:if>
                                        <c:if test="${problem.status == 'UNDER_CONSIDERATION'}">
                                            <span class="badge badge-pill badge-secondary" style="font-size: 15px">${problem.status.description}</span>
                                        </c:if>
                                        <c:if test="${problem.status == 'REJECTED'}">
                                            <span class="badge badge-pill badge-danger" style="font-size: 15px">${problem.status.description}</span>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr scope="row">
                                        <td><i class="far fa-calendar-alt"></i>
                                            <fmt:parseDate value="${ problem.creationDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                            <fmt:formatDate dateStyle="MEDIUM" value="${ parsedDateTime }" />
                                        </td>
                                        <td align="right"><i class="far fa-arrow-alt-circle-up"></i> ${problem.countOfVotes}    <i class="far fa-comment"></i> ${fn:length(problem.comments)}</td>
                                    </tr>
                                </tfoot>
                        </table>
                    </button>
                </form:form>
            </div>
        </c:forEach>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/getAddressFromCoords.js"></script>

</body>
</html>
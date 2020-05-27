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
                <h4><a href="/login">Войти <i class="fas fa-user"></i></a></h4>
            </div>
        </li>
        <li>
            <div>
                <h4><a href="/registration">Зарегистрироваться <i class="fas fa-user-plus"></i></a></h4>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <h4><a href="/logout">Выйти <i class="fas fa-user-slash"></i></a></h4>
                </sec:authorize>
        </li>
        <li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <h4><a href="/admin">Администрирование <i class="fas fa-crown"></i></a></h4>
                </sec:authorize>
            </div>
        </li>
    </ul>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<form>
    <table>
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
                </td><td>
                    <button class="btn btn-primary" type="submit" style="height: 50px">Фильтр</button>
            </div>
            </td>
            <td>
                <label>${str}</label>
            </td></tr>
        </div>
    </table>
</form>
<div class="container px-lg-5">
    <div class="row mx-lg-n5">
        <c:forEach var="problem" items="${problems}">
            <div class="col">
                <form:form method = "GET" action = "/problems/${problem.id}">
                    <button type="submit">
                        <table class="table" style="height: 250px">
                            <tbody>
                                <tr scope="row">
                                    <td>
                                        <input hidden type="text" name="problemsCoords" value="${problem.address}"/>
                                        <label name="problemsAddress"></label>
                                    </td>
                                    <td rowspan="2" width="150px" height="150px">
                                        <img src="${problem.mainPhoto}" class="rounded" width="150px" height="auto">
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot>
                                    <tr scope="row">
                                        <td><i class="far fa-calendar-alt"></i>
                                            <fmt:parseDate value="${ problem.creationDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                            <fmt:formatDate dateStyle="MEDIUM" value="${ parsedDateTime }" />
                                        </td>
                                        <td align="right"><i class="far fa-arrow-alt-circle-up"></i></i>15  <i class="far fa-comment"></i> ${fn:length(problem.comments)}</td>
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
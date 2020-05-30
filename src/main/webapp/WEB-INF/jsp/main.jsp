<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main-menu.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/format-text.css">

    <title>Главная</title>

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>

    <ul class="menu-main">
        <li class="nazvanie"><a href="/" class="current">KonohaLIVE</a></li>
        <li><a href="/problems">Проблемы</a></li>
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
<c:forEach items="${problems}" var="problem">
    <input hidden type="text" name="problemsCoords" value="${problem.address}"/>
    <input hidden type="text" name="problemsId" value="${problem.id}"/>
    <input hidden type="text" name="problemsStatus" value="${problem.status.description}"/>
    <input hidden type="text" name="problemsPhoto" value="${problem.mainPhoto}"/>
    <input hidden type="text" name="problemsDate" value="${problem.creationDate}"/>
    <input hidden type="text" name="problemsCategory" value="${problem.category.getDescription()}"/>
</c:forEach>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ya-map.js"></script>

<table style="margin: auto">
<form>
<div class="form-group">
    <div class="row-cols-sm-4">
        <tr>
        <td style="height: 50px; align-content: center;">
    <select id="category" name="category" class="categories">
        <option value="">Укажите категорию проблемы</option>
        <c:forEach items="${categories}" var="category">
            <option value="${category}">
                <c:out value="${category.description}"></c:out>
            </option>
        </c:forEach>
    </select>
        </td>
         <td style="height: 50px; align-content: center;">
       <button class="btn btn-primary" type="submit" style="height: 50px">Фильтр</button>
    </div>
    </td>
    <td style="height: 50px; align-content: center; text-align: center;">
    <label>${str}</label>
    </td>
</div>
</form>
</table>
<table style="margin: auto">
    <tr>
    <td rowspan="3">
            <div id="map" style="width: 900px; height: 800px;  margin: 0 auto; border: 3px solid #bfbfbf"></div>
    </td>
    <td>
        <span class="format-text" style="color: #606060; text-align: center;"><h5>Самая важная</h5></span>
        <div class="col-6 col-md-4">
            <form:form method = "GET" action = "/problems/${mostVotedProblem.id}">
                <button type="submit" style="border-radius: 10px 10px; border: #ff0c13; box-shadow: 0 0 5px #ff585f;">
                    <table class="table" style="height: 240px; width: 320px">
                        <tbody>
                        <tr scope="row">
                            <td height="150px" colspan="2">
                                <div style="text-align: center;">
                                    <img src="${mostVotedProblem.mainPhoto}" class="rounded"  width="auto" height="150px">
                                </div>
                            </td>
                        </tr>
                        <tr scope="row">
                            <td colspan="2" style="height: 80px">
                                <label hidden name="problemsCoords">${mostVotedProblem.address}</label>
                                <label name="problemsAddress"></label>
                            </td>
                        </tr>
                        <tr  scope="row">
                            <td height="25px">
                                <label name="problemsCategory">${mostVotedProblem.category.description}</label>
                            </td>
                            <td height="25px" align="right">
                                <span class="badge badge-pill badge-warning" style="font-size: 15px">${mostVotedProblem.status.description}</span>
                            </td>
                        </tr>
                        </tbody>
                        <tfoot>
                        <tr scope="row">
                            <td><i class="far fa-calendar-alt"></i>
                                <fmt:parseDate value="${ mostVotedProblem.creationDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                                <fmt:formatDate dateStyle="MEDIUM" value="${ parsedDateTime }" />
                            </td>
                            <td align="right"><i class="far fa-arrow-alt-circle-up"></i> ${mostVotedProblem.countOfVotes}    <i class="far fa-comment"></i> ${fn:length(mostVotedProblem.comments)}</td>
                        </tr>
                        </tfoot>
                    </table>
                </button>
            </form:form>
        </div>
    </td>
    </tr>
<tr>
<td>
<span class="format-text" style="color: #606060; text-align: center;"><h5>Самая обсуждаемая</h5></span>
<div class="col-6 col-md-4">
    <form:form method = "GET" action = "/problems/${mostCommentProblem.id}">
        <button type="submit" style="border-radius: 10px 10px; border: #ff0c13; box-shadow: 0 0 5px #ff585f;">
            <table class="table" style="height: 240px; width: 320px">
                <tbody>
                <tr scope="row">
                    <td height="150px" colspan="2">
                        <div style="text-align: center;">
                            <img src="${mostCommentProblem.mainPhoto}" class="rounded"  width="auto" height="150px">
                        </div>
                    </td>
                </tr>
                <tr scope="row">
                    <td colspan="2" style="height: 80px">
                        <label hidden name="problemsCoords">${mostCommentProblem.address}</label>
                        <label name="problemsAddress"></label>
                    </td>
                </tr>
                <tr  scope="row">
                    <td height="25px">
                        <label name="problemsCategory">${mostCommentProblem.category.description}</label>
                    </td>
                    <td height="25px" align="right">
                        <span class="badge badge-pill badge-warning" style="font-size: 15px">${mostCommentProblem.status.description}</span>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr scope="row">
                    <td><i class="far fa-calendar-alt"></i>
                        <fmt:parseDate value="${ mostCommentProblem.creationDate }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                        <fmt:formatDate dateStyle="MEDIUM" value="${ parsedDateTime }" />
                    </td>
                    <td align="right"><i class="far fa-arrow-alt-circle-up"></i> ${mostCommentProblem.countOfVotes}    <i class="far fa-comment"></i> ${fn:length(mostCommentProblem.comments)}</td>
                </tr>
                </tfoot>
            </table>
        </button>
    </form:form>
</div>
</td>
</tr>
</table>




</body>
</html>
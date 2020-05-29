<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Сообщить о проблеме</title>

    <!-- Загрузка стилей -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main-menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table-style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/buttonReportProblem.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/format-text.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/word-limit.js"></script>


    <!-- Шапка сайта -->
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

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/placemark.js"></script>

</head>
<body>
    <form:form method="post" modelAttribute="problem" enctype="multipart/form-data">
        <div>
            <table class="reportProblems" align="center">
                <tr>
                    <td align="center" style="font-size: 24px">
                        <b>Сообщить о проблеме</b>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <div id="map" style="width: 90%; height: 300px; margin: 10px auto; border: 3px solid #bfbfbf"></div>
                        <input class="form-control" type="text" id="myInput" placeholder="Укажите точку на карте" disabled readonly/>
                        <form:hidden path="address" name="myInputBD" id="myInputBD"/>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <form:select path="category" class="categories">
                            <option selected disabled>Укажите категорию проблемы</option>
                            <c:forEach var="categ" items="${categories}">
                                <form:option value="${categ}">${categ.getDescription()}</form:option>
                            </c:forEach>
                        </form:select>
                        <div>Выберите категорию проблемы. Это важно, поскольку проблема поступит на рассмотрение соответствующему ведомству</div>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <form:textarea class="form-control" rows="3" onkeyup="WordLimit()" name="descText" id="descText" path="description" type="textarea" placeholder="Описание проблемы" cssStyle="width: 90%; resize: none;"/>
                        <div class="counter">Осталось символов: <span id="wordCounter">255</span></div>
                    </td>
                </tr>
                <tr>
                    <td align="center" style=" height: 50px;">
                        <form:input class="form-control" path="files" type="file" maxlength="5" multiple="multiple" accept="image/*,image/jpeg" />
                    </td>
                </tr>
                <input hidden id="authorLogin" name="authorLogin" value="${pageContext.request.userPrincipal.name}"/>
                <tr>
                    <td>
                        <button id="createBtn" class="glo" type="submit">Отправить</button>
                    </td>
                </tr>
            </table>
        </div>
    </form:form>
</body>
</html>

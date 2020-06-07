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

    <script defer src="${pageContext.request.contextPath}/resources/js/all.js"></script>

    <title>Проблемы</title>


    <ul class="menu-main">
        <li class="nazvanie"><a href="/">KonohaLIVE</a></li>
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


<div>
    <br> На сайте запрещается:
    <ul>
        <li>
            Публиковать ссылки или загружать на сайт нелицензионное коммерческое программное обеспечение, программы для его взлома и генераторы ключей, а также на материалы, защищённые авторскими правами (книги, музыка, видео и прочее).
        </li>
        <li>
            Спамить/добавлять рекламные посты/комментарии/материалы без согласования с администрацией.
        </li>
        <li>
            Выкладывать контент порнографического содержания (картинки, ссылки и т.д.)
        </li>
        <li>
            Размещать чужую персональную информацию.
        </li>
        <li>
            Оскорблять других пользователей, прямой или косвенной форме, высказывать неуважение и/или хамить участникам форума, использовать ненормативную лексику.
        </li>
        <li>
            Разжигать национальную/расовую/религиозную вражду.
        </li>
        <li>
            Просить денежной помощи и т.д.
        </li>
        <li>
            Публиковать непроверенную информацию.
        </li>
        <li>
            Вести разговор на темы, не соответствующие данному форуму, или отсылать сообщения, не соответствующие обсуждаемой теме.
        </li>
        <li>
            Создавать сообщения, не несущие конкретной смысловой нагрузки в контексте обсуждаемой темы.
        </li>
        <li>
            Самовольное модерирование.
        </li>
        <li>
            Обсуждение действий и мер, предпринимаемых модератором или администратором.
        </li>
    </ul>
</div>


</body>
</html>
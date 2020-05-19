<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сообщить о проблеме</title>

    <!-- Загрузка стилей -->
    <link rel="stylesheet" href="../../css/main-menu.css">
    <link rel="stylesheet" href="../../css/table-style.css">
    <link rel="stylesheet" href="../../css/buttonReportProblem.css">

    <!-- Шапка сайта -->
    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="/">Главная</a></li>
        <li><a href="/problems">Проблемы</a></li>
        <li><a href="/news">Новости</a></li>
        <li><form action="/problems/new ">
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/placemark.js"></script>

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
                        <div id="map" style="width: 90%; height: 300px; margin: 10px auto"></div>
                        <input type="text" id="myInput" placeholder="Укажите точку на карте"/>
                        <form:hidden path="address" name="myInputBD" id="myInputBD"/>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <form:select path="category" class="categories">
                            <option selected disabled>Укажите категорию проблемы:</option>
                            <c:forEach var="categ" items="${categories}">
                                <form:option value="${categ}">${categ}</form:option>
                            </c:forEach>
                        </form:select>
                        <em>Выберите категорию проблемы. Это важно, поскольку проблема поступит на рассмотрение соответствующему ведомству</em>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <form:input path="description" type="comment" placeholder="Описание проблемы" style="height: 100px"/>
                    </td>
                </tr>
                <tr>

                    <td align="center" style="height: 50px;">
                        <form:input path="files" type="file" multiple="multiple" accept="image/*,image/jpeg" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="glo" type="submit" onlclick="ZdesDoljenBitRedirect()">Отправить</button>
                    </td>
                </tr>
            </table>
        </div>
    </form:form>














</body>
</html>

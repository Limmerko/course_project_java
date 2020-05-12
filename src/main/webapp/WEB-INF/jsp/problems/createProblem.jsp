<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить проблему</title>
    <link rel="stylesheet" href="../css/main-menu.css">
    <link rel="stylesheet" href="../css/table-style.css">
    <link rel="stylesheet" href="../css/buttonReportProblem.css">

    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="">Главная</a></li>
        <li><a href="" class="current">Проблемы</a></li>
        <li><a href="">Новости</a></li>
        <li><form action="reportProblem.html">
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>

    <script src="https://api-maps.yandex.ru/2.1/?apikey=1d2ebd06-147f-4d5c-bcf3-0922e11867eb&lang=ru_RU" type="text/javascript">
    </script>
    <script type="text/javascript">
        ymaps.ready(init);
        function init(){
            var myMap = new ymaps.Map("map", {
                center: [56.14656449, 40.40288382],
                zoom: 13
            });
        }
    </script>

</head>
<body>
<!--<form method="post" enctype="multipart/form-data">
    <input type="file" name="file" multiple accept="image/*,image/jpeg">
<button class="glo" type="submit" >Отправить</button> -->
    <form:form method="post" modelAttribute="problem" >
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
                        <input path="address" type="text" class="inputProblem" placeholder="Укажите точку на карте">
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
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <form:input path="description" type="comment" class="inputProblem" placeholder="Описание проблемы" style="height: 100px"/>
                    </td>
                </tr>
                <tr>
                    <td align="center" style="height: 50px;">
                        <input type="file" multiple accept="image/*,image/jpeg">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="glo" type="submit">Отправить</button>
                    </td>
                </tr>
            </table>
        </div>
    </form:form>
</body>
</html>
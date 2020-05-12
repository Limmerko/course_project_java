<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/main-menu.css">
    <link rel="stylesheet" href="css/buttonReportProblem.css">
    <title>Главная</title>

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
    <script src="placemark.js" type="text/javascript"></script>

    <ul class="menu-main">
        <li class="nazvanie">KonohaLIVE</li>
        <li><a href="" class="current">Главная</a></li>
        <li><a href="">Проблемы</a></li>
        <li><a href="">Новости</a></li>
        <li><form action="reportProblem.html">
            <button class="glo" type="submit">Сообщить о проблеме</button>
        </form></li>
    </ul>

</head>
<body>
<h1>yo konoha</h1>

<select id="categories" class="categories" style="width: 500px; height: 40px">
    <option selected disabled>Укажите категорию проблемы:</option>
    <option value="road">Дорожные проблемы</option>
    <option value="social">Социальная сфера</option>
    <option value="urban">Городское хозяйство</option>
</select>
<div id="map" style="width: 95%; height: 950px;  margin: 0 auto"></div>



</body>
</html>
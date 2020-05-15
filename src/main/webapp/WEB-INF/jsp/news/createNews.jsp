<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

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


    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
           integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

<div class="container">
    <div class="row">
        <div class="col-sm">
        <form:form method="post" action="/news/new" modelAttribute="news" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">Заголовок</label>
                <form:input class="form-control" id="title" path="title" type="text" placeholder="Заголовок"/>
            </div>

            <div class="form-group">
                <label for="description">Описание</label>
             <form:input class="form-control" id="description" path="description" type="text" placeholder="Описание"/>
            </div>

            <div class="form-group">
                <label for="photos">Фотографии</label>
             <form:input class="form-control" id="photos" path="files" type="file" multiple="multiple" accept="image/*,image/jpeg"/>
            </div>
             <button class="glo" data-toggle="tooltip"
                     data-placement="top" title="Создать новость" type="submit">Отправить</button>
        </form:form>
        </div>
    </div>
</div>
</body>
</html>
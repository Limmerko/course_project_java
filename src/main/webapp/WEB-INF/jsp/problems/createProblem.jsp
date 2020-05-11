<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>JSP</title></head>
<body>
<h1>Report Problems</h1>
<!--<form method="post" enctype="multipart/form-data">
    <input type="file" name="file" multiple accept="image/*,image/jpeg">
<button class="glo" type="submit" >Отправить</button> -->
    <form>
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
                        <input type="text" placeholder="Укажите точку на карте">
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <select id="categories" class="categories">
                            <option selected disabled>Укажите категорию проблемы:</option>
                            <option value="road">Дорожные проблемы</option>
                            <option value="social">Социальная сфера</option>
                            <option value="urban">Городское хозяйство</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <input type="comment" placeholder="Описание проблемы" style="height: 100px">
                    </td>
                </tr>
                <tr>
                    <td align="center" style="height: 50px;">
                        <input type="file" multiple accept="image/*,image/jpeg">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="glo" type="submit" >Отправить</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>
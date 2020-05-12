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
                        <form:input path="address" type="text" placeholder="Укажите точку на карте"/>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <form:select path="category">
                            <option selected disabled>Укажите категорию проблемы:</option>
                            <c:forEach var="categ" items="${categories}">
                                <form:option value="${categ}">${categ}</form:option>
                            </c:forEach>
                        </form:select>
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
                        <button class="glo" type="submit" >Отправить</button>
                    </td>
                </tr>
            </table>
        </div>
    </form:form>
</body>
</html>
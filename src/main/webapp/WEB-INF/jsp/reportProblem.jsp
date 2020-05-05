<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>JSP</title></head>
<body>
<h1>Reposrt Problems</h1>
<form method="post" enctype="multipart/form-data">
    <input type="file" name="file" multiple accept="image/*,image/jpeg">
<button class="glo" type="submit" >Отправить</button>
</form>
<table>
    <tr>
        <td>OriginalFileName:</td>
        <td>${file.originalFilename}</td>
    </tr>
    <tr>
        <td>Type:</td>
        <td>${file.contentType}</td>
        <td><img src="${img}"/> </td>
    </tr>
</table>
</body>
</html>
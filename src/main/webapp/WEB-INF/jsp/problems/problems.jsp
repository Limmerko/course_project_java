<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>JSP</title></head>
<body>
<h1>Problems</h1>
    <c:forEach var="item" items="${problems}">
        ${item}
    </c:forEach>
</body>
</html>
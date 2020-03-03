<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>

    Site 전체 Exception 처리 페이지

    <h3>
        <i class="fa fa-warning text-red"></i>
        ${exception.getMessage()}
    </h3>
    <ul>
        <c:forEach items="${exception.getStackTrace()}" var="stack">
            <li>${stack.toString()}</li>
        </c:forEach>
    </ul>

</body>
</html>

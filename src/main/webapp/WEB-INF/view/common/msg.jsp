<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jack-test</title>

    <!-- Bootstrap -->
    <link href="/resources/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
<script>
    alert("${msg}");
    location.href="${pageContext.request.contextPath}${loc}";
</script>
</body>
</html>
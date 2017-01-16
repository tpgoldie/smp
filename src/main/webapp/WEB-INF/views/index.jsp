<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
    <title><spring:message code="welcome"/></title>
</head>
<body>
    <div>
        <p id="welcome-id"><spring:message code="welcome"/></p>
        <p id="login-id"><input type="submit" value='<spring:message code="login"/>'></p>
    </div>
</body>

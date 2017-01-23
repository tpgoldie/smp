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

        <form:form method="POST" action="/smp/login" commandName="userModel">
            <table>
                <tr>
                    <td><spring:message code="username.label"/></td>
                    <td><form:input id="username-id" path="username" /></td>
                    <td><form:errors path="username" cssStyle="color: #ff0000;"/></td>
                </tr>
                <tr>
                    <td><spring:message code="password.label"/></td>
                    <td><form:input id="secure-token-id", path="secureToken" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Submit"></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>

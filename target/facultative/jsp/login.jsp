<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety" %>
<%@ page import="by.trjava.pashkovich.facultative.constants.JspPath" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
</head>
<body>
<div class="container">
    <div class="text-center form-name"><fmt:message key="local.header.login" bundle="${loc}"/></div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form method="post" action="${pageContext.request.contextPath}/mainController">
                <div class="form-element">
                    <label><fmt:message key="local.form.login" bundle="${loc}"/></label>
                    <input type="text" name="login">
                </div>

                <div class="form-element">
                    <label><fmt:message key="local.form.password" bundle="${loc}"/></label>
                    <input type="password" name="password">
                </div>
                <label class="error-text">
                    ${informMessage}
                </label>
                <input type="hidden" name="command" value="${CommandVariety.LOGIN}">
                <input type="submit" value="<fmt:message key="local.menu.login" bundle="${loc}"/>" class="button">
                <input type="button" value="<fmt:message key="local.menu.registration" bundle="${loc}"/>" class="button"
                       id="registration-button"
                       onclick="location.href='${pageContext.request.contextPath}${JspPath.REGISTRATION_PAGE}'">
                <input type="button" value="<fmt:message key="local.menu.main" bundle="${loc}"/>" class="button"
                       onclick="location.href='${pageContext.request.contextPath}${JspPath.INDEX_PAGE}'">
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
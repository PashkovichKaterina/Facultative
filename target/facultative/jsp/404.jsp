<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.constants.JspPath" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>

<div class="container">
    <div class="row error-block">
        <div class="col-md-6 col-sm-12 text-right align-self-center">
            <img src="${pageContext.request.contextPath}/images/404.png">
        </div>
        <div class="col-md-6 col-sm-12 align-self-center">
            <p class="error-message">
                <fmt:message key="local.error404.message1" bundle="${loc}"/>
                <br/>
                <fmt:message key="local.error404.message2" bundle="${loc}"/>
            </p>
            <input type="button" onclick="location.href='${pageContext.request.contextPath}${JspPath.INDEX_PAGE}'"
                   value="<fmt:message key="local.menu.main" bundle="${loc}"/>">
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
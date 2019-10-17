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
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top" id="navbar-main">
    <c:import url="${JspPath.HEADER}"/>
</nav>
<header class="header">
    <div class="container">
        <div class="description">
            <h1>
                <fmt:message key="local.index.header" bundle="${loc}"/>
                <p>
                    <fmt:message key="local.index.description" bundle="${loc}"/>
                </p>
            </h1>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="blog">
        <h1 class="text-center">
            <fmt:message key="local.index.offer" bundle="${loc}"/>
        </h1>
        <div class="row text-center">
            <div class="col-md-4 col-sm-12">
                <img src="images/adv1.png" class="adv-image">
                <p class="advant-title"><fmt:message key="local.index.advantage1" bundle="${loc}"/></p>
                <p class="advant-text"><fmt:message key="local.index.description1" bundle="${loc}"/></p>
            </div>
            <div class="col-md-4 col-sm-12">
                <img src="images/adv2.png" class="adv-image">
                <p class="advant-title"><fmt:message key="local.index.advantage2" bundle="${loc}"/></p>
                <p class="advant-text"><fmt:message key="local.index.description2" bundle="${loc}"/></p>
            </div>
            <div class="col-md-4 col-sm-12">
                <img src="images/adv3.png" class="adv-image">
                <p class="advant-title"><fmt:message key="local.index.advantage3" bundle="${loc}"/></p>
                <p class="advant-text"><fmt:message key="local.index.description3" bundle="${loc}"/></p>
            </div>
        </div>
    </div>

    <div class="col-md-12 text-center mb-5">
        <button class="btn btn-outline-secondary btn-lg button"
                onclick="location.href='mainController?command=${CommandVariety.VIEW_ALL_COURSE}'">
            <fmt:message key="local.menu.course.list" bundle="${loc}"/>
        </button>
    </div>
</div>
<c:import url="jsp/footer.jsp"/>

<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src='js/main.js'></script>
</body>
</html>
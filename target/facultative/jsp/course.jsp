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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top ">
    <c:import url="${JspPath.HEADER}"/>
</nav>
<div class="blog">
    <div class="container">
        <h1 class="text-center"><fmt:message key="local.menu.course.list" bundle="${loc}"/></h1>
        <div class="row course-padding">
            <div class="col-md-9 d1">
                <form class="search-line" method="post" action="mainController">
                    <input type="hidden" name="command" value="${CommandVariety.VIEW_SEARCH_COURSE_RESULT}">
                    <input type="text" placeholder="<fmt:message key="local.course.search" bundle="${loc}"/>"
                           name="title" value="${title}">
                    <button type="submit"></button>
                </form>
            </div>
            <div class="col-md-3">
                <div class="dropdown open">
                    <button class="btn dropdown-toggle button" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="local.course.category" bundle="${loc}"/>
                    </button>
                    <div class="dropdown-menu dropdown-button" aria-labelledby="dropdownMenuButton">
                        <c:forEach var="category" items="${categories}">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.SEARCH_COURSE}&category=${category}">
                                    ${category}
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        ${informMessage}
        <c:set var="i" value="${0}"/>
        <c:forEach var="course" items="${courses}">
            <c:if test="${course.availability == true}">
                <c:if test="${i % 4 == 0}">
                    <div class="row course-padding">
                </c:if>
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="card">
                        <p class="course-title">${course.title}</p>
                        <p class="course-type">
                            <fmt:message key="local.course.category" bundle="${loc}"/>: ${course.category}
                        </p>
                        <p class="course-text">
                                ${course.description.substring(0, 100)}...
                        </p>
                        <div class="card-footer">
                            <a class="card-link"
                               href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.VIEW_COURSE_INFO}&id=${course.id}">
                                <fmt:message key="local.course.detail" bundle="${loc}"/>
                            </a>
                        </div>
                    </div>
                </div>
                <c:if test="${(i + 1) % 4 == 0 || (i + 1) == courses.size()}">
                    </div>
                </c:if>
                <c:set var="i" value="${i + 1}"/>
            </c:if>
        </c:forEach>
    </div>
</div>

<c:import url="${JspPath.FOOTER}"/>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
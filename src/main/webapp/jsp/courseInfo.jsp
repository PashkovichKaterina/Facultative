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

<div class="container-fluid anket">
    <h1 class="text-center"><fmt:message key="local.header.course.info" bundle="${loc}"/></h1>
    <div class="row anket-info">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="row person-card">
                <div class="col-md-6 text-center align-self-center">
                    <img src="${pageContext.request.contextPath}/images/courseInfo.png">
                </div>
                <div class="col-md-6 align-self-center">
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.info.title" bundle="${loc}"/>: </p>
                        <p class="answer">${course.getTitle()}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.category" bundle="${loc}"/>: </p>
                        <p class="answer">${course.getCategory()}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.info.teacher" bundle="${loc}"/>: </p>
                        <p class="answer">${course.getTeacher()}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.info.classes" bundle="${loc}"/>: </p>
                        <p class="answer">${course.getClassesNumber()}</p>
                    </div>
                    <div class="error-text">
                        ${informMessage}
                    </div>
                    <c:if test="${applyButton!=null && applyButton == true}">
                        <a href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.APPLY}&id=${course.getId()}">
                            <input type="button" value="<fmt:message key="local.course.info.request" bundle="${loc}"/>">
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
    <div class="row ">
        <div class="col-md-1"></div>
        <div class="col-md-10 course-info">
            <p>
                ${course.getDescription()}
            </p>
            <c:if test="${requirement.size() != 0}">
                <div>
                    <p class="font-weight-bold"><fmt:message key="local.course.info.skill" bundle="${loc}"/>:</p>
                    <ul>
                        <c:forEach var="num" items="${requirement.entrySet()}">
                            <li class="ml-5">${num.getKey()} - ${num.getValue()}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <c:if test="${timetable.size() != 0}">
                <div>
                    <p class="font-weight-bold"><fmt:message key="local.course.info.timetable" bundle="${loc}"/>:</p>
                    <ul>
                        <c:forEach var="num" items="${timetable.entrySet()}">
                            <li class="ml-5">${num.getKey()} - ${num.getValue()}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<c:import url="${JspPath.FOOTER}"/>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
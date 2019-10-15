<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <h1 class="text-center"><fmt:message key="local.header.current.course" bundle="${loc}"/></h1>
    <div class="row anket-info">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="row person-card">
                <div class="col-md-6 text-center ">
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
                        <p class="question"><fmt:message key="local.course.info.average.mark" bundle="${loc}"/>: </p>
                        <p class="answer">${mark}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.info.classes" bundle="${loc}"/>: </p>
                        <ul class="ml-3 mt-2">
                            <li><fmt:message key="local.course.info.classes.total" bundle="${loc}"/>
                                - ${course.getClassesNumber()}</li>
                            <li><fmt:message key="local.course.info.classes.passed" bundle="${loc}"/>
                                - ${classesCount}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
    <div class="row ">
        <div class="col-md-1"></div>
        <div class="col-md-10 course-info">
            <h2><fmt:message key="local.course.info.marks" bundle="${loc}"/></h2>
            <c:if test="${marks!=null && marks.size()!=0}">
                <table class="table">
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="local.work.title" bundle="${loc}"/></th>
                        <th><fmt:message key="local.course.info.mark" bundle="${loc}"/></th>
                    </tr>
                    <tbody>
                    <c:set var="i" value="${1}"/>
                    <c:forEach var="num" items="${marks.entrySet()}">
                        <tr>
                            <th scope="row">${i}</th>
                            <td>${num.getKey()}</td>
                            <td>${num.getValue()}</td>
                        </tr>
                        <c:set var="i" value="${i + 1}"/>
                    </c:forEach>
                    </tbody>
                </table>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="http://trjava.by/pashkovich/facultative" %>

<%@ page import="by.trjava.pashkovich.facultative.constants.JspPath" %>
<%@ page import="by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety" %>

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

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2 admin-left-panel">
            <p class="admin-panel-button"
               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_ALL_COURSE_FOR_ADMIN}'">
                <fmt:message key="local.header.courses" bundle="${loc}"/>
            </p>
            <p class="admin-panel-button"
               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_ALL_CATEGORY}'">
                <fmt:message key="local.header.categories" bundle="${loc}"/>
            </p>
            <p class="admin-panel-button"
               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_ALL_SKILL}'">
                <fmt:message key="local.header.skills" bundle="${loc}"/>
            </p>
            <p class="admin-panel-active"
               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_ALL_TEACHER}'">
                <fmt:message key="local.header.teachers" bundle="${loc}"/>
            </p>
            <p class="admin-panel-button"
               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_ALL_STUDENT}'">
                <fmt:message key="local.header.students" bundle="${loc}"/>
            </p>
            <p class="admin-panel-button">
                <fmt:message key="local.header.applies" bundle="${loc}"/>
            </p>
        </div>
        <div class="col-md-10  mt-5">
            <div class="admin-title">
                <h1><fmt:message key="local.header.teachers" bundle="${loc}"/></h1>
                <form class="search-line d1">
                    <input type="hidden" name="command" value="${CommandVariety.SEARCH_TEACHER}">
                    <input type="text" placeholder="<fmt:message key="local.teacher.name" bundle="${loc}"/>..."
                           name="name">
                    <button type="submit"></button>
                </form>
            </div>
            <input type="button" class="admin-button" value="<fmt:message key="local.button.add" bundle="${loc}"/>">
            <input type="button" class="admin-button" value="<fmt:message key="local.button.delete" bundle="${loc}"/>">
            <table class="table">
                <tr>
                    <th>#</th>
                    <th></th>
                    <th><fmt:message key="local.teacher.name" bundle="${loc}"/></th>
                    <th><fmt:message key="local.form.position" bundle="${loc}"/></th>
                    <th><fmt:message key="local.form.phone" bundle="${loc}"/></th>
                    <th><fmt:message key="local.courses.count" bundle="${loc}"/></th>
                </tr>
                <tbody>
                <c:set var="i" value="${1}"/>
                <c:forEach var="teacher" items="${teachers.entrySet()}">
                    <tr>
                        <th scope="row">${i} <i class="fa fa-pencil" aria-hidden="true"></i></th>
                        <td><input type="checkbox" name="adminCourse"></td>
                        <td>${teacher.key.surname} ${teacher.key.name} ${teacher.key.patronymic}</td>
                        <td>${teacher.key.position}</td>
                        <td>${teacher.key.phone}</td>
                        <td>${teacher.value}</td>
                    </tr>
                    <c:set var="i" value="${i + 1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<c:import url="${JspPath.FOOTER}"/>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
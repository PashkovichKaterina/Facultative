<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety" %>

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
    <div class="text-center form-name"><fmt:message key="local.header.review" bundle="${loc}"/></div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form method="post" action="mainController">
                <input type="hidden" name="command" value="${CommandVariety.LEAVE_REVIEW}">
                <input type="hidden" name="student" value="${student.id}">
                <input type="hidden" name="course" value="${course.id}">
                <div class="form-element">
                    <label><b><fmt:message key="local.student.name"
                                           bundle="${loc}"/>:</b> ${student.surname} ${student.name} ${student.patronymic}
                    </label>
                </div>
                <div class="form-element">
                    <label><b><fmt:message key="local.course.title" bundle="${loc}"/>:</b> ${course.title}</label>
                </div>
                <div class="form-element">
                    <label><fmt:message key="local.courses.review" bundle="${loc}"/></label>
                    <textarea rows="7" name="review">${review}</textarea>
                </div>
                <input type="submit" value="<fmt:message key="local.button.save" bundle="${loc}"/>" class="button">
                <input type="button" value="<fmt:message key="local.button.cancel" bundle="${loc}"/>" class="button"
                       onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.FIXED_COURSE}&id=${course.id}'"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
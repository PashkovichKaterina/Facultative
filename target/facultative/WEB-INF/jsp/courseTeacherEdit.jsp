<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<div class="container-fluid anket">
    <h1 class="text-center"><fmt:message key="local.header.fixed.course" bundle="${loc}"/></h1>
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
                        <p class="answer">${course.title}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.category" bundle="${loc}"/>: </p>
                        <p class="answer">${course.category}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.info.teacher" bundle="${loc}"/>: </p>
                        <p class="answer">${course.teacher}</p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.course.info.classes" bundle="${loc}"/>: </p>
                        <p class="answer">${course.classesNumber}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
    <div class="trickery text-center">
        <input id=tab-1 type=radio name=tab value=content-1 checked>

        <div class="tabs">
            <label for="tab-1"><fmt:message key="local.student.list" bundle="${loc}"/></label>
        </div>
        <div class="container-fluid student-table">
            <form method="post" action="mainController">
                <input type="hidden" name="command" value="${CommandVariety.EDIT_STUDENT_MARK}">
                <input type="hidden" name="id" value="${course.id}">
                <input type="hidden" name="work" value='${work}'>
                <div id=content-1 class="student-table-change">
                    <table class="table simulacion mx-auto">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="local.student.name" bundle="${loc}"/></th>
                            <th class="text-left">${work}</th>
                        </tr>
                        <tbody>
                        <c:set var="i" value="${1}"/>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <th scope="row">1</th>
                                <td>
                                        ${student.key.surname} ${student.key.name} ${student.key.patronymic}
                                </td>
                                <td>
                                    <select class="form-control" name="${student.key.id}">
                                        <c:forEach var="mark" begin="0" end="10">
                                            <c:if test="${student.value == mark}">
                                                <option selected>${mark}</option>
                                            </c:if>
                                            <c:if test="${student.value != mark}">
                                                <option>${mark}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                    <p class="table-pages text-left ml-3 w-50">
                        <input type="submit" class="button"
                               value="<fmt:message key="local.button.save" bundle="${loc}"/>">
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

<c:import url="${JspPath.FOOTER}"/>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
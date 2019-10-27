<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" uri="http://trjava.by/pashkovich/facultative" %>

<%@ page import="by.trjava.pashkovich.facultative.constants.JspPath" %>
<%@ page import="by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety" %>
<%@ page import="by.trjava.pashkovich.facultative.util.ReplacingEscapedCharacter" %>

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
                    <c:if test="${endCourseButton == true}">
                        <input type="button" value="Закончить курс"
                               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.END_COURSE}&id=${course.getId()}'">
                    </c:if>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
    <div class="trickery text-center">
        <input id=tab-1 type=radio name=tab value=content-1 checked>
        <input id=tab-2 type=radio name=tab value=content-2>
        <input id=tab-3 type=radio name=tab value=content-3>

        <div class="tabs">
            <label for="tab-1"><fmt:message key="local.student.list" bundle="${loc}"/></label>
            <label for="tab-2"><fmt:message key="local.class.list" bundle="${loc}"/></label>
            <label for="tab-3"><fmt:message key="local.works" bundle="${loc}"/></label>
        </div>

        <div class="container-fluid student-table">
            <div id=content-1 class="student-table-change">
                <c:if test="${marks == null || marks.size() == 0}">
                    <div class="empty-collection">
                        <fmt:message key="local.student.empty" bundle="${loc}"/>
                    </div>
                </c:if>
                <c:if test="${marks != null && marks.size() != 0}">
                    <table class="table simulacion mx-auto">
                        <tr>
                            <th class="table-student-number">#</th>
                            <th class="table-student"><fmt:message key="local.student.name" bundle="${loc}"/></th>
                            <c:forEach var="work" items="${works}">
                                <th class="table-student">${work}
                                    <i class="fa fa-pencil" aria-hidden="true"
                                       onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_EDIT_MARK_FORM}&id=${course.getId()}&work=${ReplacingEscapedCharacter.replace(work)}'"></i>
                                </th>
                            </c:forEach>
                        </tr>
                        <tbody>
                        <c:set var="i" value="${1}"/>
                        <c:forEach var="markElement" items="${marks.entrySet()}">
                            <tr>
                                <th scope="row">
                                        ${i}
                                    <i class="fa fa-comment" aria-hidden="true"
                                       title="<fmt:message key="local.button.review" bundle="${loc}"/>"
                                       onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_LEAVE_REVIEW_FORM}&student=${markElement.key.id}&course=${course.id}'"></i>

                                </th>
                                <td>
                                    <a href="#">
                                            ${markElement.key.surname} ${markElement.key.name} ${markElement.key.patronymic}

                                    </a>
                                </td>
                                <c:forEach var="mark" items="${markElement.value}">
                                    <td>${mark}</td>
                                </c:forEach>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id=content-2 class="student-table-change">
                <c:if test="${classes == null || classes.size() == 0}">
                    <div class="empty-collection">
                        <fmt:message key="local.class.empty" bundle="${loc}"/>
                    </div>
                </c:if>
                <c:if test="${classes != null && classes.size() != 0}">
                    <table class="table">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="local.form.date" bundle="${loc}"/></th>
                            <th><fmt:message key="local.form.time" bundle="${loc}"/></th>
                        </tr>
                        <tbody>
                        <c:set var="i" value="${1}"/>
                        <c:forEach var="classElement" items="${classes}">
                            <tr>
                                <th scope="row">${i}</th>
                                <td><custom:outDate date="${classElement}"/></td>
                                <td><custom:outTime date="${classElement}"/></td>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${marks != null && marks.size() != 0 && classes.size() < course.getClassesNumber()}">
                    <input type="button" value="<fmt:message key="local.class.add" bundle="${loc}"/>"
                           onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.ADD_CLASS_FORM}&id=${course.id}'">
                </c:if>
            </div>
            <div id=content-3 class="student-table-change">
                <c:if test="${works == null || works.size() == 0}">
                    <div class="empty-collection">
                        <fmt:message key="local.work.empty" bundle="${loc}"/>
                    </div>
                </c:if>
                <c:if test="${works != null && works.size() != 0}">
                    <table class="table">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="local.work.title" bundle="${loc}"/></th>
                        </tr>
                        <tbody>
                        <c:set var="i" value="${1}"/>
                        <c:forEach var="work" items="${works}">
                            <tr>
                                <th scope="row">${i}</th>
                                <td>${work}</td>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${marks != null && marks.size() != 0 && classes.size() < course.getClassesNumber()}">
                    <input type="button" value="<fmt:message key="local.work.add" bundle="${loc}"/>"
                           onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.ADD_WORK_FORM}&id=${course.getId()}'">
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:import url="${JspPath.FOOTER}"/>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
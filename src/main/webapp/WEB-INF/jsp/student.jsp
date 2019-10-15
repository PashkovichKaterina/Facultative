<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.entity.characteristics.UserRole" %>
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
    <h1 class="text-center"><fmt:message key="local.header.account.student" bundle="${loc}"/></h1>
    <div class="row anket-info">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="row person-card align-self-center">
                <div class="col-md-6 text-center">
                    <img src="${pageContext.request.contextPath}/images/student.png">
                </div>
                <div class="col-md-6 align-self-center">
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.form.surname" bundle="${loc}"/>: </p>
                        <p class="answer">
                            ${user.getSurname()}
                            <c:if test="${user.getSurname()==null}">
                                -
                            </c:if>
                        </p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.form.name" bundle="${loc}"/>: </p>
                        <p class="answer">
                            ${user.getName()}
                            <c:if test="${user.getName()==null}">
                                -
                            </c:if>
                        </p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.form.patronymic" bundle="${loc}"/>: </p>
                        <p class="answer">
                            ${user.getPatronymic()}
                            <c:if test="${user.getPatronymic()==null}">
                                -
                            </c:if>
                        </p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.form.birth.date" bundle="${loc}"/>: </p>
                        <p class="answer">
                            ${user.getDateOfBirthString()}
                            <c:if test="${user.getDateOfBirth()==null}">
                                -
                            </c:if>
                        </p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.form.phone" bundle="${loc}"/>: </p>
                        <p class="answer">
                            ${user.getPhone()}
                            <c:if test="${user.getPhone()==null}">
                                -
                            </c:if>
                        </p>
                    </div>
                    <div class="qa-element">
                        <p class="question"><fmt:message key="local.form.address" bundle="${loc}"/>: </p>
                        <p class="answer">
                            ${user.getAddress()}
                            <c:if test="${user.getAddress()==null || user.getAddress().length()==0}">
                                -
                            </c:if>
                        </p>
                    </div>
                    <c:if test="${user.getRole() == UserRole.STUDENT}">
                        <input type="button" value="<fmt:message key="local.button.edit" bundle="${loc}"/>"
                               onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.EDIT_STUDENT_FORM}'">
                    </c:if>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
    <div class="trickery text-center">
        <input id=tab-1 type=radio name=tab value=content-1 checked>
        <input id=tab-2 type=radio name=tab value=content-2>
        <input id=tab-3 type=radio name=tab value=content-3>

        <div class="tabs">
            <label for="tab-1"><fmt:message key="local.courses.current" bundle="${loc}"/></label>
            <label for="tab-2"><fmt:message key="local.courses.passed" bundle="${loc}"/></label>
            <label for="tab-3"><fmt:message key="local.courses.requests" bundle="${loc}"/></label>
        </div>

        <div class="container-fluid student-table">
            <div id=content-1 class="student-table-change">
                <c:if test="${currentCourse.size()==0}">
                    <div class="empty-collection">
                        <fmt:message key="local.courses.current.empty" bundle="${loc}"/>
                    </div>
                </c:if>
                <c:if test="${currentCourse.size()!=0}">
                    <table class="table">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="local.course.title" bundle="${loc}"/></th>
                            <th><fmt:message key="local.course.start.date" bundle="${loc}"/></th>
                            <th><fmt:message key="local.course.info.mark" bundle="${loc}"/></th>
                        </tr>
                        <tbody>
                        <c:set var="i" value="${1}"/>
                        <c:forEach var="num" items="${currentCourse}">
                            <tr>
                                <th scope="row">${i}</th>
                                <td>
                                    <a href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.CURRENT_COURSE}&id=${num.getCourse().getId()}">
                                            ${num.getCourse().getTitle()}
                                    </a>
                                </td>
                                <td>${num.getBeginDate()}</td>
                                <td>${num.getMark()}</td>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div id=content-2 class="student-table-change">
                <c:if test="${archive.size()==0}">
                    <div class="empty-collection">
                        <fmt:message key="local.courses.passed.empty" bundle="${loc}"/>
                    </div>
                </c:if>
                <c:if test="${archive.size()!=0}">
                <table class="table">
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="local.course.title" bundle="${loc}"/></th>
                        <th><fmt:message key="local.course.start.date" bundle="${loc}"/></th>
                        <th><fmt:message key="local.course.end.date" bundle="${loc}"/></th>
                        <th><fmt:message key="local.course.info.average.mark" bundle="${loc}"/></th>
                    </tr>
                    <tbody>
                    <c:set var="i" value="${1}"/>
                    <c:forEach var="num" items="${archive}">
                        <tr>
                            <th scope="row">${i}</th>
                            <td>
                                <a href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.VIEW_COURSE_INFO}&id=${num.getCourse().getId()}">
                                        ${num.getCourse().getTitle()}
                                </a>
                            </td>
                            <td>${num.getBeginDate()}</td>
                            <td>${num.getEndDate()}</td>
                            <td>0</td>
                        </tr>
                        <c:set var="i" value="${i + 1}"/>
                    </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <div id=content-3 class="student-table-change">
                <c:if test="${apply.size()==0}">
                    <div class="empty-collection">
                        <fmt:message key="local.courses.requests.empty" bundle="${loc}"/>
                    </div>
                </c:if>
                <c:if test="${apply.size()!=0}">
                    <table class="table">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="local.course.title" bundle="${loc}"/></th>
                            <th><fmt:message key="local.course.status" bundle="${loc}"/></th>
                        </tr>
                        <tbody>
                        <c:set var="i" value="${1}"/>
                        <c:forEach var="num" items="${apply}">
                            <tr>
                                <th scope="row">${i}</th>
                                <td>
                                    <a href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.VIEW_COURSE_INFO}&id=${num.getCourse().getId()}">
                                            ${num.getCourse().getTitle()}
                                    </a>
                                </td>
                                <td>${num.getStatus()}</td>
                            </tr>
                            <c:set var="i" value="${i + 1}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:import url="${JspPath.FOOTER}"/>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
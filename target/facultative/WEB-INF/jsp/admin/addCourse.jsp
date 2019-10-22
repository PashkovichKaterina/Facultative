<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety" %>

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
    <div class="text-center form-name"><fmt:message key="local.header.add.course" bundle="${loc}"/></div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form method="post" action="mainController">
                <input type="hidden" name="command" value="${CommandVariety.ADD_COURSE}">
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.title.ru" bundle="${loc}"/>
                        <span class="error-text">
                            *${titleRuError}
                        </span>
                    </label>
                    <input type="text" name="title_ru" value="${title_ru}">
                </div>
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.title.en" bundle="${loc}"/>
                        <span class="error-text">
                            *${titleEnError}
                        </span>
                    </label>
                    <input type="text" name="title_en" value="${title_en}">
                </div>

                <div class="form-element">
                    <label><fmt:message key="local.course.category" bundle="${loc}"/></label>
                    <select name="category">
                        <c:forEach var="category" items="${categories}">
                            <option value="${category}">${category}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-element">
                    <label><fmt:message key="local.teacher.name" bundle="${loc}"/></label>
                    <select name="teacher">
                        <c:forEach var="teacher" items="${teachers}">
                            <option value="${teacher.id}">
                                    ${teacher.surname} ${teacher.name} ${teacher.patronymic}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-element skills-require">
                    <label><fmt:message key="local.course.info.skill" bundle="${loc}"/></label>
                    <div id="require-element">
                        <select class="selected-column skill-element" name="skill1">
                            <c:forEach var="skill" items="${skills}">
                                <option value="${skill}">
                                        ${skill}
                                </option>
                            </c:forEach>
                        </select>
                        <select class="selected-column" name="level1">
                            <c:forEach var="level" items="${levels}">
                                <option value="${level}">
                                        ${level}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <p class="m-0 mt-2 mb-3" id="add-skill"><fmt:message key="local.button.add" bundle="${loc}"/></p>
                <div class="form-element timetable">
                    <label><fmt:message key="local.course.info.timetable" bundle="${loc}"/></label>
                    <div id="timetable-element">
                        <select class="selected-column" name="day1">
                            <c:forEach var="day" items="${days}">
                                <option value="${day}">
                                        ${day}
                                </option>
                            </c:forEach>
                        </select>
                        <input type="text" class="selected-column time" name="time1">
                    </div>
                </div>
                <p class="m-0 mt-2 mb-3" id="add-timetable"><fmt:message key="local.button.add" bundle="${loc}"/></p>
                <div class="form-element">
                    <label>
                        <fmt:message key="local.course.info.classes" bundle="${loc}"/>
                        <span class="error-text">
                            *${countError}
                        </span>
                    </label>
                    <input type="number" min="1" name="count"  value="${count}">
                </div>
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.description.ru" bundle="${loc}"/>
                        <span class="error-text">
                            *${descriptionRuError}
                        </span>
                    </label>
                    <textarea maxlength="200" name="description_ru"  value="${description_ru}"></textarea>
                </div>
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.description.en" bundle="${loc}"/>
                        <span class="error-text">
                            *${descriptionEnError}
                        </span>
                    </label>
                    <textarea maxlength="200" name="description_en"  value="${description_en}"></textarea>
                </div>
                <input type="submit" value="<fmt:message key="local.button.add" bundle="${loc}"/>" class="button">
                <input type="button" value="<fmt:message key="local.button.cancel" bundle="${loc}"/>" class="button"
                       onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.SHOW_ALL_COURSE_FOR_ADMIN}'">
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/maskedinput.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/main.js'></script>
</body>
</html>
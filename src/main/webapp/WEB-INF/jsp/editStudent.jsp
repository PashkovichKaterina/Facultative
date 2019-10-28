<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.controller.command.provider.CommandVariety" %>
<%@ page import="by.trjava.pashkovich.facultative.constants.JspPath" %>

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
    <div class="form-name"><fmt:message key="local.header.edit.student" bundle="${loc}"/></div>
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form method="post" action="${pageContext.request.contextPath}/mainController">
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.surname" bundle="${loc}"/>
                        <span class="error-text">
                            *${surnameError}
                        </span>
                    </label>
                    <input type="text" name="surname" value="${surname}">
                </div>

                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.name" bundle="${loc}"/>
                        <span class="error-text">
                            *${nameError}
                        </span>
                    </label>
                    <input type="text" name="name" value="${name}">
                </div>

                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.patronymic" bundle="${loc}"/>
                        <span class="error-text">
                            *${patronymicError}
                        </span>
                    </label>
                    <input type="text" name="patronymic" value="${patronymic}">
                </div>
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.birth.date" bundle="${loc}"/>
                        <span class="error-text">
                            ${dataError}
                        </span>
                    </label>
                    <input type="date" name="date" value="${date}">
                </div>
                <div class="form-element">
                    <label>
                        <fmt:message key="local.form.phone" bundle="${loc}"/>
                        <span class="error-text">
                            *${phoneError}
                        </span>
                    </label>
                    <input type="text" id="phone" name="phone" value="${phone}">
                </div>
                <div class="form-element">
                    <label><fmt:message key="local.form.address" bundle="${loc}"/></label>
                    <input type="text" name="address" value="${address}">
                </div>
                <input type="hidden" name="command" value="${CommandVariety.EDIT_STUDENT}">
                <input type="submit" value="<fmt:message key="local.button.save" bundle="${loc}"/>" class="button">
                <input type="button" value="<fmt:message key="local.button.skip" bundle="${loc}"/>" class="button"
                       onclick="location.href='${pageContext.request.contextPath}/mainController?command=${CommandVariety.ACCOUNT}'">
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/maskedinput.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
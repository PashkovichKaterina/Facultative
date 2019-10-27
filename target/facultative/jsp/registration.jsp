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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
</head>
<body>
<div class="container">
    <div class="form-name"><fmt:message key="local.menu.registration" bundle="${loc}"/></div>
    <form method="post" action="${pageContext.request.contextPath}/mainController">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <form method="post">
                    <div class="form-element">
                        <label>
                            <fmt:message key="local.form.login" bundle="${loc}"/>
                            <span class="error-text">
                                *${loginError}
                            </span>
                        </label>
                        <input type="text" name="login" value="${login}">
                    </div>

                    <div class="form-element">
                        <label>
                            <fmt:message key="local.form.email" bundle="${loc}"/>
                            <span class="error-text">
                                *${emailError}
                            </span>
                        </label>
                        <input type="email" name="email" value="${email}">
                    </div>

                    <div class="form-element">
                        <label>
                            <fmt:message key="local.form.password" bundle="${loc}"/>
                            <span class="error-text">
                                *${passwordError}
                            </span>
                        </label>
                        <input type="password" name="password">
                    </div>

                    <div class="form-element">
                        <label>
                            <fmt:message key="local.form.confirm" bundle="${loc}"/>
                            <span class="error-text">
                                *${confirmError}
                            </span>
                        </label>
                        <input type="password" name="confirm">
                    </div>
                    <input type="hidden" name="command" value="${CommandVariety.REGISTRATION}">
                    <input type="submit" value="<fmt:message key="local.menu.registration" bundle="${loc}"/>"
                           class="button">
                    <input type="button" value="<fmt:message key="local.menu.login" bundle="${loc}"/>" class="button"
                           onclick="location.href='${pageContext.request.contextPath}${JspPath.LOGIN_PAGE}'">
                    <input type="button" value="<fmt:message key="local.menu.main" bundle="${loc}"/>" class="button"
                           onclick="location.href='${pageContext.request.contextPath}${JspPath.INDEX_PAGE}'">
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
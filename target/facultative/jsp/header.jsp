<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="by.trjava.pashkovich.facultative.controller.command.variety.CommandVariety" %>
<%@ page import="by.trjava.pashkovich.facultative.constants.JspPath" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<a class="navbar-brand" href="${pageContext.request.contextPath}${JspPath.INDEX_PAGE}">
    <fmt:message key="local.menu.main" bundle="${loc}"/>
</a>
<a class="lang"
   href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.CHANGE_LANGUAGE}&local=ru">
    <fmt:message key="local.language.ru" bundle="${loc}"/>
</a> |
<a class="lang"
   href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.CHANGE_LANGUAGE}&local=en">
    <fmt:message key="local.language.en" bundle="${loc}"/>
</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <i class="fa fa-bars" aria-hidden="true"></i>
</button>
<div class="collapse navbar-collapse text-center" id="navbarSupportedContent">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-value="about"
               href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.VIEW_ALL_COURSE}">
                <fmt:message key="local.menu.course.list" bundle="${loc}"/>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link " data-value="portfolio" href="#">
                <fmt:message key="local.menu.about" bundle="${loc}"/>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link " data-value="contact" href="#">
                <fmt:message key="local.menu.contact" bundle="${loc}"/>
            </a>
        </li>
        <c:if test="${user == null}">
            <li class="nav-item">
                <a class="nav-link " data-value="contact"
                   href="${pageContext.request.contextPath}${JspPath.LOGIN_PAGE}">
                    <fmt:message key="local.menu.login" bundle="${loc}"/>
                </a>
            </li>
        </c:if>
        <c:if test="${user != null}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${user.login}
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.ACCOUNT}">
                        <fmt:message key="local.menu.account" bundle="${loc}"/>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/mainController?command=${CommandVariety.LOGOUT}">
                        <fmt:message key="local.menu.logout" bundle="${loc}"/>
                    </a>
                </div>
            </li>
        </c:if>
    </ul>
</div>
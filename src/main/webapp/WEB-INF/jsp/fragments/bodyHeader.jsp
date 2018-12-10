<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="/voting" class="navbar-brand"><img src="resources/images/icon-meal.png"> <spring:message code="app.title"/></a>
        <form class="form-inline my-2">
            <c:if test = "${isAdmin}">
                <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
            </c:if>
            <span class="navbar-brand"> ${userName} </span>
            <a class="btn btn-primary" href="logout">
                <span class="fa fa-sign-out"></span>
            </a>
        </form>
    </div>
</nav>

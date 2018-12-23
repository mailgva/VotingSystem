<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="/voting" class="navbar-brand"><img src="resources/images/icon-meal.png"> <spring:message code="app.title"/></a>
        <form class="form-inline my-2">
            <c:if test = "${isAdmin}">
                <%--<a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>--%>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="users"><spring:message code="user.title"/></a>
                        <a class="dropdown-item" href="dishes"><spring:message code="common.dishes"/></a>
                        <a class="dropdown-item" href="restaurants"><spring:message code="common.restaurants"/></a>
                        <a class="dropdown-item" href=""><spring:message code="common.dailyMenu"/></a>
                        <a class="dropdown-item" href="votes"><spring:message code="common.votes"/></a>
                    </div>
                </div>
            </c:if>
            <%--<span class="navbar-brand"> ${userName} </span>--%>
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link " href="#">${userName}</a>
                <a class="btn btn-primary" href="logout">
                    <span class="fa fa-sign-out"></span>
                </a>
            </nav>


        </form>
    </div>
</nav>

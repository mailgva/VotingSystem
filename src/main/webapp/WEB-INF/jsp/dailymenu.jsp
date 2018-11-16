<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="menu.voting"/></h3>
    <form method="post" action="voting">
        <dl>
            <dt><spring:message code="common.date"/>:</dt>
            <dd><input type="date" name="date" value="${(param.date != null ? param.date : dateMenu)}"></dd>
        </dl>
        <button type="submit"><spring:message code="common.select"/></button>
    </form>
    <hr/>
    <form method="post" action="voting/vote">
        <input type="hidden" name="voteId" value="${voteId}">
        <input type="hidden" name="date" value="${(param.date != null ? param.date : dateMenu)}">

        <c:set var = "restoName" scope = "page" value = ""/>
        <c:forEach items="${dailyMenus}" var="dailyMenu">
            <jsp:useBean id="dailyMenu" scope="page" type="com.voting.to.DailyMenuTo"/>
            <c:if test = "${!(restoName.equals(dailyMenu.resto.name))}">
                <c:if test = "${!(restoName.length()==0)}">
                        </table>
                    </dd>
                </dl>
                </c:if>
                <c:set var = "restoName" value = "${dailyMenu.resto.name}"/>
                <dl>
                    <dt data-restSelected="${dailyMenu.selected}">
                        <input type="radio" id="${dailyMenu.resto.id}" name="restoId" value="${dailyMenu.resto.id}"
                            <c:if test = "${dailyMenu.selected}">
                                checked="checked"
                            </c:if>
                        >
                        <label for="restoId_${dailyMenu.resto.id}">${dailyMenu.resto.name}</label>
                    </dt>
                    <dd>
                        <table border="1" cellpadding="4" cellspacing="0">
                            <thead>
                            <tr>
                                <th style="width:400px;"><spring:message code="menu.title"/></th>
                                <th><spring:message code="menu.price"/></th>
                            </tr>
                            </thead>
            </c:if>
                            <tr>
                                <td>${dailyMenu.dish.name}</td>
                                <td>${dailyMenu.dish.price}</td>
                            </tr>
        </c:forEach>
                        </table>
                    </dd>
                </dl>
        <button type="submit"><spring:message code="common.vote"/></button>
    </form>

    <%--<table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Ресторан</th>
            <th>Блюдо</th>
            <th>Цена</th>
        </tr>
        </thead>
        <c:forEach items="${dailyMenus}" var="dailyMenu">
            <jsp:useBean id="dailyMenu" scope="page" type="com.voting.to.DailyMenuTo"/>
            <tr data-restSelected="${dailyMenu.selected}">
                <td>${dailyMenu.resto.name}</td>
                <td>${dailyMenu.dish.name}</td>
                <td>${dailyMenu.dish.price}</td>
            </tr>
        </c:forEach>
    </table>--%>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
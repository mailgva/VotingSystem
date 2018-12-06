<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://voting.com/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/voting.common.js" defer></script>
<script type="text/javascript" src="resources/js/voting.votes.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <h3><spring:message code="menu.voting"/></h3>
        <form method="post" action="voting" id="filter">
            <dl>
                <dt><spring:message code="common.date"/>:</dt>
                <dd><input type="date" name="date" value="${(param.date != null ? param.date : dateMenu)}"></dd>
                <button type="submit" class="btn btn-success"><spring:message code="common.select"/></button>
            </dl>
        </form>
        <hr/>
        <form method="post" action="voting/vote" id="vote">
            <input type="hidden" name="voteId" value="${voteId}">
            <input type="hidden" name="date" value="${(param.date != null ? param.date : dateMenu)}">
            <div class="row">
                <c:forEach items="${dailyMenus}" var="dailyMenu">
                    <jsp:useBean id="dailyMenu" scope="page" type="com.voting.to.DailyMenuTo"/>
                    <dl data-restSelected="${dailyMenu.selected}">
                        <dt >
                            <input type="radio" id="${dailyMenu.resto.id}" name="restoId" value="${dailyMenu.resto.id}"
                            <c:if test = "${dailyMenu.selected}">
                                   checked="checked"
                            </c:if>
                            >
                            <label for="restoId_${dailyMenu.resto.id}">${dailyMenu.resto.name}</label>
                        </dt>
                        <dd >
                            <table class="table table-bordered" <%--border="1" cellpadding="4" cellspacing="0"--%>>
                                <thead class="thead-dark">
                                <tr>
                                    <th style="width:250px;"><spring:message code="menu.title"/></th>
                                    <th><spring:message code="menu.price"/></th>
                                </tr>
                                </thead>
                                <c:forEach items="${dailyMenu.resto.dishes}" var="dishes">
                                    <jsp:useBean id="dishes" scope="page" type="com.voting.model.Dish"/>
                                    <tr>
                                        <td>${dishes.name}</td>
                                        <td>${dishes.price}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </dd>
                    </dl>
                </c:forEach>
            </div>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
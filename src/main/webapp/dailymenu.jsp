<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Меню на день</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Меню на день</h2>
    <form method="post" action="dailymenu?action=filter">
        <dl>
            <dt>From Date:</dt>
            <dd><input type="date" name="date" value="${param.date}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Ресторан</th>
            <th>Блюдо</th>
            <th>Цена</th>
        </tr>
        </thead>
        <c:forEach items="${dailyMenus}" var="dailyMenu">
            <jsp:useBean id="dailyMenu" scope="page" type="com.voting.to.DailyMenuTo"/>
            <tr data-mealExcess="${dailyMenu.selected}">
                <td>${dailyMenu.resto.name}</td>
                <td>${dailyMenu.dish.name}</td>
                <td>${dailyMenu.dish.price}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.urise.webapp.model.*" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullname" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <p>
            <c:forEach var="type" items="<%=ContactType.values()%>">
        <dl>
            <dt>${type.title}</dt>
            <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
        </dl>
        </c:forEach>
        </p>
        <h3>Секции:</h3>
        <c:forEach var="type1" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type1)}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>

        <dt>${type1.title}</dt><br>
            <c:choose>
                <c:when test="${type1=='PERSONAL' || type1=='OBJECTIVE'}">
                    <textarea  name='${type1}' cols=100 rows=3> <%=section%> </textarea><br>
                </c:when>
                <c:when test="${type1=='QUALIFICATIONS' || type1=='ACHIEVEMENT'}">
                    <textarea name='${type1}' cols=100
                              rows=5><%=String.join("\n", ((BulletedListSection) section).getList())%></textarea><br>
                </c:when>
                <c:when test="${type1=='EXPERIENCE' || type1=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
                        <dl>
                            <dt>Название организации:</dt>
                            <dd><input type="text" name='${type1}' size=100 value="${org.link.name}"></dd>
                        </dl>
                        <dl>
                            <dt>Сайт организации:</dt>
                            <dd><input type="text" name='${type1}url' size=100 value="${org.link.url}"></dd>
                            </dd>
                        </dl>
                        <c:forEach var="pos" items="${org.positions}">
                            <jsp:useBean id="pos" type="com.urise.webapp.model.Organization.Position"/>
                            <dl>
                                <dt>Дата старта работы:</dt>
                                <dd>
                                    <input type="text" name="${type1}${counter.index}startDate" size=10
                                           value="<%=pos.getStartDate()%>" >
                                </dd>
                            </dl>
                            <dl>
                                <dt>Дата окончания работы:</dt>
                                <dd>
                                    <input type="text" name="${type1}${counter.index}endDate" size=10
                                           value="<%=pos.getEndDate()%>" >
                            </dl>
                            <dl>
                                <dt>Должность в организации:</dt>
                                <dd><input type="text" name='${type1}${counter.index}title' size=75
                                           value="${pos.title}">
                            </dl>
                            <dl>
                                <dt>Описание должности:</dt>
                                <dd><textarea name="${type1}${counter.index}description" rows=5
                                              cols=75>${pos.description}</textarea></dd>
                            </dl>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>

        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
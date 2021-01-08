<%@ page import="com.urise.webapp.model.BulletedListSection" %>
<%@ page import="com.urise.webapp.model.SimpleTextSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <section>
        <p>
            <c:forEach var="sectionEntry" items="${resume.sections}">
                <jsp:useBean id="sectionEntry"
                             type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.AbstractSection>"/>
                <c:set var="type" value="${sectionEntry.key}"/>
                <c:set var="section" value="${sectionEntry.value}"/>
                <jsp:useBean id="section" type="com.urise.webapp.model.AbstractSection"/>
                ${type.title}<br>
            <c:choose>
            <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">

        <li><%=((SimpleTextSection)section).getDescription()%></li>
        <br>
        <hr>
        </c:when>
        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">

            <c:forEach var="item" items="<%=((BulletedListSection) section).getList()%>">
                <li>
                        ${item}
                </li>
                <br>
            </c:forEach>
            <hr>

        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">

            <c:forEach var="item" items="<%=((OrganizationSection) section).getOrganizations()%>">
                <c:forEach var="pos" items="${item.positions}">
                    <li>
                            ${pos}
                    </li>
                    <br>
                </c:forEach>
            </c:forEach>
            <hr>

        </c:when>
        </c:choose>
        </c:forEach>
        </p>
    </section>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
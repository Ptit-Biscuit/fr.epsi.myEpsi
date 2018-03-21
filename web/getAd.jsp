<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="header.jsp" %>
</head>

<body>
    <% for (Ad ad : (List<Ad>) pageContext.getRequest().getAttribute("ads")) { %>
    <jsp:include page="adCard.jsp">
        <jsp:param name="id" value="<%= ad.getId() %>"/>
        <jsp:param name="status" value="<%= ad.getStatus().name().toLowerCase() %>"/>
        <jsp:param name="title" value="<%= ad.getTitle() %>"/>
        <jsp:param name="desc" value="<%= ad.getDescription() %>"/>
        <jsp:param name="price" value="<%= ad.getPrice() %>"/>
        <jsp:param name="seller" value="<%= ad.getSeller() %>"/>
        <jsp:param name="detail" value="true"/>
    </jsp:include>
    <% } %>
</body>
</html>
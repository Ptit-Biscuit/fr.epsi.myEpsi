<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="header.jsp" %>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
</head>

<body>
<div class="card-deck" style="max-width: 98%">
    <% for (Ad ad : (List<Ad>) pageContext.getRequest().getAttribute("ads")) { %>
    <jsp:include page="adCard.jsp">
        <jsp:param name="id" value="<%= ad.getId() %>"/>
        <jsp:param name="title" value="<%= ad.getTitle() %>"/>
        <jsp:param name="desc" value="<%= ad.getDescription() %>"/>
        <jsp:param name="price" value="<%= ad.getPrice() %>"/>
        <jsp:param name="detail" value="true"/>
    </jsp:include>
    <% } %>
</div>
</body>
</html>
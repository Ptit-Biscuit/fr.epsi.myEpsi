<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% int adNumber = 0;
    List<Ad> ads = (List<Ad>) pageContext.getRequest().getAttribute("ads"); %>

<html>
<head>
    <%@ include file="header.jsp" %>
</head>

<body>
<% for (int i = 0; i < ads.size(); i++) {
    if (adNumber++ % 4 == 0) { %>
<div class="card-deck" style="max-width: 98%">
        <% } %>

    <jsp:include page="adCard.jsp">
        <jsp:param name="id" value="<%= ads.get(i).getId() %>"/>
        <jsp:param name="status" value="<%= ads.get(i).getStatus().name().toLowerCase() %>"/>
        <jsp:param name="title" value="<%= ads.get(i).getTitle() %>"/>
        <jsp:param name="desc" value="<%= ads.get(i).getDescription() %>"/>
        <jsp:param name="price" value="<%= ads.get(i).getPrice() %>"/>
        <jsp:param name="seller" value="<%= ads.get(i).getSeller() %>"/>
        <jsp:param name="modification"
                   value="<%= ads.get(i).getModificationAt() != null ? ads.get(i).getModificationAt() : ' ' %>"/>
        <jsp:param name="detail" value="true"/>
        <jsp:param name="canBuy" value="true"/>
    </jsp:include>

        <% if (adNumber % 4 == 0) {
    	out.println("</div>");
    }
} %>
</body>
</html>
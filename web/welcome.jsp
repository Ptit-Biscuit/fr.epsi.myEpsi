<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="fr.epsi.myEpsi.models.beans.User" %>
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
<% if (pageContext.getAttribute("error") != null)
    out.println("<div class=\"error\">" + pageContext.getAttribute("error") + "</div>");
%>

<img src="https://media.giphy.com/media/jxJjBMvqEvMSA/giphy.gif"/>

<br/>

<% if (((User) pageContext.getSession().getAttribute("user")).getMail().contains("@root")) { %>
<a href="${pageContext.request.contextPath}/administration">Administration</a>

<br/>
<% } %>

<a href="${pageContext.request.contextPath}/logout">Déconnexion</a>

<br/>

<a href="${pageContext.request.contextPath}/createAd">Créer une annonce</a>

<div class="card-deck" style="max-width: 98%">
    <% for (Ad ad : (List<Ad>) pageContext.getRequest().getAttribute("ads")) { %>
    <jsp:include page="adCard.jsp">
        <jsp:param name="id" value="<%= ad.getId() %>"/>
        <jsp:param name="title" value="<%= ad.getTitle() %>"/>
        <jsp:param name="desc" value="<%= ad.getDescription() %>"/>
        <jsp:param name="price" value="<%= ad.getPrice() %>"/>
        <jsp:param name="detail" value="true"/>
        <jsp:param name="canBuy" value="true"/>
    </jsp:include>
    <% } %>
</div>
</body>
</html>

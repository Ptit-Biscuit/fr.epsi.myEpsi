<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="fr.epsi.myEpsi.models.beans.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="header.jsp" %>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
</head>

    <body>
        <% if (pageContext.getAttribute("error") != null)
            out.println("<div class=\"error\">" + pageContext.getAttribute("error") + "</div>");
        %>

        <img src="https://media.giphy.com/media/jxJjBMvqEvMSA/giphy.gif"/>

        <br />

        <% if (((User) pageContext.getSession().getAttribute("user")).getMail().contains("@root")) { %>
        <a href="${pageContext.request.contextPath}/administration">Administration</a>

        <br/>
        <% } %>

        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>

        <br/>

        <a href="${pageContext.request.contextPath}/createAd">Créer une annonce</a>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2">Titre</div>
                <div class="col-sm-1">Status</div>
                <div class="col-sm-3">Description</div>
                <div class="col-sm-2">Vendeur</div>
                <div class="col-sm-1">Prix</div>
            </div>

            <% for (Ad ad : (List<Ad>) pageContext.getRequest().getAttribute("ads")) { %>
            <div class="row">
                <div class="col-sm-2"><%= ad.getTitle() %>
                </div>
                <div class="col-sm-1"><%= ad.getStatus().name().toLowerCase() %>
                </div>
                <div class="col-sm-3"><%= ad.getDescription() %>
                </div>
                <div class="col-sm-2"><%= ad.getSeller() %>
                </div>
                <div class="col-sm-1"><%= ad.getPrice() %>
                </div>
            </div>
            <% } %>
        </div>
    </body>
</html>

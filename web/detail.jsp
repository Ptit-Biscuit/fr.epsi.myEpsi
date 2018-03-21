<%@ page import="fr.epsi.myEpsi.models.EStatus" %>
<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="fr.epsi.myEpsi.models.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Ad ad = (Ad) request.getAttribute("ad");
    boolean userOwnAd = ad.getSeller().equals(((User) request.getSession().getAttribute("user")).getMail()); %>

<html>
<head>
    <%@ include file="header.jsp" %>
</head>

<body>
<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/detail">
    <div class="card">
        <div class="card-header">
            <h5 class="card-title text-center">
                <% if (userOwnAd) { %>
                <input class="form-control" type="text" name="adTitle" value="<%= ad.getTitle() %>"
                       maxlength="140" autofocus required/>
                <% } else
                    out.println(ad.getTitle()); %>
            </h5>
            <p class="card-text">
                <%= ad.getStatus().name().toLowerCase() + (ad.getStatus().equals(EStatus.VENDUE) ? " (" + ad.getBuyer() + ")" : "") %>
            </p>
            <p class="card-text text-right">
                <%= ad.getViewNumber() %> vue(s)
            </p>
        </div>

        <div class="card-body text-secondary">
            <p class="card-text">
                <% if (userOwnAd) { %>
                <input class="form-control" type="text" name="adDescription"
                       value="<%= ad.getDescription()%>"/>
                <% } else
                    out.println(ad.getDescription()); %>
            </p>
            <p class="card-text">
                <% if (userOwnAd) { %>
                <input class="form-control" type="number" name="adPrice" value="<%= ad.getPrice() %>"
                       min="0" step="any" required/>
                <% } else
                    out.println(ad.getPrice()); %>
            </p>
            <p class="card-text text-right">
                <%= ad.getSeller() %>
            </p>
            <p class="card-text text-right">
                <%= ad.getModificationAt() != null ? ad.getModificationAt() : "" %>
            </p>
        </div>

        <div class="card-footer">
            <input type="hidden" name="adId" value="<%= ad.getId() %>"/>

            <% if (request.getSession().getAttribute("user") != null) {
                if (userOwnAd) { %>
            <button type="submit" class="btn btn-secondary">Valider</button>
            <% } %>
            <a href="${pageContext.request.contextPath}/welcome" class="btn btn-secondary">Retour</a>
            <% } else { %>
            <a href="${pageContext.request.contextPath}/index.jsp">Retour</a>
            <% } %>
        </div>
    </div>
</form>
</body>
</html>

<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.epsi.myEpsi.models.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@ include file="header.jsp" %>

    <body>
        <% if (pageContext.getAttribute("error") != null)
            out.println("<div class=\"error\">" + pageContext.getAttribute("error") + "</div>");
        %>

        <img src="https://media.giphy.com/media/jxJjBMvqEvMSA/giphy.gif"/>

        <div>
            Bienvenue <%= ((User) pageContext.getSession().getAttribute("user")).getMail() %> !!
        </div>

        <br />

        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>

        <br/>

        <a href="${pageContext.request.contextPath}/createAd">Créer une annonce</a>

        <div>
            <table>
                <th>
                    <td>Titre</td>
                    <td>Status</td>
                    <td>Description</td>
                    <td>Vendeur</td>
                    <td>Prix</td>
                </th>
                <% for (Ad ad : ((List<Ad>)pageContext.getRequest().getAttribute("ads")) ) {
                    out.println("<tr>");

                    out.println("<td>" + ad.getTitle() + "</td>");
                    out.println("<td>" + ad.getStatus() + "</td>");
                    out.println("<td>" + ad.getDescription() + "</td>");
                    out.println("<td>" + ad.getSeller() + "</td>");
                    out.println("<td>" + ad.getPrice() + "</td>");

                    out.println("</tr>");
                } %>
            </table>
        </div>
    </body>
</html>

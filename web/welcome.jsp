<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8"/>

        <title>LeBonKoinKoin</title>

        <link rel="stylesheet" type="text/css" href="css/style.css"/>
    </head>

    <body>
        <img src="https://media.giphy.com/media/jxJjBMvqEvMSA/giphy.gif"/>

        <div>
            Bienvenue <%= pageContext.getRequest().getParameter("userMail") %> !!
        </div>

        <br />

        <a href="/disconnect">Déconnexion</a>

        <br/>

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

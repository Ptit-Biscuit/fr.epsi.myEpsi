<%@ page import="fr.epsi.myEpsi.controlers.database.interfaces.IAdDao" %>
<%@ page import="fr.epsi.myEpsi.controlers.database.interfaces.IUserDao" %>
<%@ page import="fr.epsi.myEpsi.models.EStatus" %>
<%@ page import="fr.epsi.myEpsi.models.beans.Ad" %>
<%@ page import="fr.epsi.myEpsi.models.beans.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ include file="header.jsp" %>
    <%@ include file="navigationBar.jsp" %>
    <meta charset="UTF-8"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>
<% if (pageContext.getRequest().getAttribute("userAlreadyExists") != null) { %>
<div class="error">
    <p>Cette adresse mail est déjà utilisée</p>
</div>
<% } %>

<br/>

<h1>Utilisateurs</h1>

<table id="annonces">
    <tr>
        <th>Mail</th>
        <th>Pseudo</th>
        <th>Mot de passe</th>
        <th>Date d'inscription</th>
        <th>Actions</th>
    </tr>
    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
        <tr>
            <td><input class="form-control" type="email" name="mail" placeholder="Adresse mail" maxlength="64"/></td>
            <td><input class="form-control" type="text" name="pseudo" placeholder="Pseudonyme" maxlength="20"/></td>
            <td><input class="form-control" type="password" name="password" placeholder="Mot de passe" minlength="8"/></td>
            <td><input class="form-control" type="password" name="confirm" placeholder="Confirmation mot de passe"
                       minlength="8"/></td>
            <td>
                <button class="btn btn-secondary" type="submit" name="adCreation" value="true">Ajouter</button>
            </td>
        </tr>
    </form>
    <% for (User user : ((IUserDao) pageContext.getSession().getAttribute("userDao")).getAllUsers()) { %>
    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
        <tr>
            <td><input class="form-control" type="email" name="mail" value="<%= user.getMail() %>"/></td>
            <td><input class="form-control" type="text" name="pseudo" value="<%= user.getPseudo() %>"/></td>
            <td><input class="form-control" type="text" name="password" value="<%= user.getPassword() %>"/></td>
            <td><%= SimpleDateFormat.getInstance().format(user.getSubsciption().getTime()) %></td>
            <td>
                <input type="hidden" name="oldMail" value="<%= user.getMail() %>"/>
                <button class="btn btn-secondary" type="submit" name="userModification" value="true">Modifier</button>

                <% if (!user.getMail().endsWith("@root")) { %>
                <button class="btn btn-secondary" type="submit" name="userDelete" value="true">Supprimer</button>
                <% } %>
            </td>
        </tr>
    </form>
    <% } %>
</table>


<h1>Annonces</h1>

<table id="annonces">
    <tr>
        <th>Titre</th>
        <th>Description</th>
        <th>Statut</th>
        <th>Vendeur</th>
        <th>Prix</th>
        <th>Vendu le</th>
        <th>Nombre de vues</th>
        <th>Modifié le</th>
        <th>Actions</th>
    </tr>
    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
    <tr>
        <td><input class="form-control" type="text" name="adTitle" maxlength="140"/></td>
        <td><input class="form-control" type="text" name="adDescription" maxlength="255"/></td>
        <td><input type="hidden" name="adStatus" value="<%= EStatus.VALIDE.ordinal() %>"/></td>
        <td><input class="form-control" type="email" name="adSeller" maxlength="64"/></td>
        <td><input class="form-control" type="number" name="adPrice" min="0" step="any"/></td>
        <td><input type="hidden"/></td>
        <td><input type="hidden"/></td>
        <td><input type="hidden"/></td>
        <td>
            <button class="btn btn-secondary" type="submit"  name="adCreation" value="true">Ajouter</button>
        </td>
    </tr>
    </form>
    <% for (Ad ad : ((IAdDao) pageContext.getSession().getAttribute("adDao")).getAllAds()) { %>
    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
    <tr>
        <td><%= ad.getTitle() %></td>
        <td><%= ad.getDescription() %></td>
        <td><%= ad.getStatus().name().toLowerCase() %></td>
        <td><%= ad.getSeller() %></td>
        <td><%= ad.getPrice() %></td>
        <td><%= ad.getSoldAt() != null ? ad.getSoldAt() : "" %></td>
        <td><%= ad.getViewNumber() %></td>
        <td><%= ad.getModificationAt() != null ? ad.getModificationAt() : "" %></td>
        <td>
            <input type="hidden" name="adId" value="<%= ad.getId() %>"/>
            <button class="btn btn-secondary" type="submit" name="adValidate" value="true">Valider</button>
            <button class="btn btn-secondary" type="submit" name="adDelete" value="true">Supprimer</button>
        </td>
    </tr>
    </form>
    <% } %>
</table>

</body>
</html>

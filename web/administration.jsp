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

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">Mail</div>
        <div class="col-sm-2">Pseudo</div>
        <div class="col-sm-3">Mot de passe</div>
        <div class="col-sm-2">Date d'inscription</div>
        <div class="col-sm-2">Actions</div>
    </div>

    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
        <div class="col-sm-2">
            <input class="form-control" type="email" name="mail" placeholder="Adresse mail" maxlength="64"/>
        </div>
        <div class="col-sm-2">
            <input class="form-control" type="text" name="pseudo" placeholder="Pseudonyme" maxlength="20"/>
        </div>
        <div class="col-sm-3">
            <input class="form-control" type="password" name="password" placeholder="Mot de passe" minlength="8"/>
        </div>
        <div class="col-sm-2">
            <input class="form-control" type="password" name="confirm" placeholder="Confirmation mot de passe"
                   minlength="8"/>
        </div>
        <div class="col-sm-2">
            <button type="submit" name="userCreation" value="true">ajouter</button>
        </div>
    </form>

    <% for (User user : ((IUserDao) pageContext.getSession().getAttribute("userDao")).getAllUsers()) { %>
    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
        <div class="col-sm-2">
            <input class="form-control" type="email" name="mail" value="<%= user.getMail() %>"/>
        </div>
        <div class="col-sm-2">
            <input class="form-control" type="text" name="pseudo" value="<%= user.getPseudo() %>"/>
        </div>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="password" value="<%= user.getPassword() %>"/>
        </div>
        <div class="col-sm-2">
            <%= SimpleDateFormat.getInstance().format(user.getSubsciption().getTime()) %>
        </div>
        <div class="col-sm-2">
            <input type="hidden" name="oldMail" value="<%= user.getMail() %>"/>
            <button type="submit" name="userModification" value="true">modifier</button>

            <% if (!user.getMail().endsWith("@root")) { %>
            <button type="submit" name="userDelete" value="true">supprimer</button>
            <% } %>
        </div>
    </form>
    <% } %>
</div>

<h1>Annonces</h1>

<table>
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
    <% for (Ad ad : ((IAdDao) pageContext.getSession().getAttribute("adDao")).getAllAds()) { %>
    <tr>
        <th><%= ad.getTitle() %></th>
        <th><%= ad.getDescription() %></th>
        <th><%= ad.getStatus().name().toLowerCase() %></th>
        <th><%= ad.getSeller() %></th>
        <th><%= ad.getPrice() %></th>
        <th><%= ad.getSoldAt() != null ? ad.getSoldAt() : "" %></th>
        <th><%= ad.getViewNumber() %></th>
        <th><%= ad.getModificationAt() != null ? ad.getModificationAt() : "" %></th>
        <th>
            <input type="hidden" name="adId" value="<%= ad.getId() %>"/>
            <button type="submit" name="adValidate" value="true">Valider</button>
            <button type="submit" name="adDelete" value="true">Supprimer</button>
        </th>
    </tr>
    <% } %>
</table>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">Titre</div>
        <div class="col-sm-2">Description</div>
        <div class="col-sm-1">Status</div>
        <div class="col-sm-2">Vendeur</div>
        <div class="col-sm-1">Prix</div>
        <div class="col-sm-1">Vendu le</div>
        <div class="col-sm-1">Nombre de vues</div>
        <div class="col-sm-1">Modifié le</div>
        <div class="col-sm-1">Actions</div>
    </div>

    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
        <div class="col-sm-2">
            <input class="form-control" type="text" name="adTitle" maxlength="140"/>
        </div>
        <div class="col-sm-2">
            <input class="form-control" type="text" name="adDescription" maxlength="255"/>
        </div>
        <div class="col-sm-1">
            <input type="hidden" name="adStatus" value="<%= EStatus.VALIDE.ordinal() %>"/>
        </div>
        <div class="col-sm-2">
            <input class="form-control" type="email" name="adSeller" maxlength="64"/>
        </div>
        <div class="col-sm-1">
            <input class="form-control" type="number" name="adPrice" min="0" step="any"/>
        </div>
        <div class="col-sm-1">
            <input type="hidden"/>
        </div>
        <div class="col-sm-1">
            <input type="hidden"/>
        </div>
        <div class="col-sm-1">
            <input type="hidden"/>
        </div>
        <div class="col-sm-1">
            <button type="submit" name="adCreation" value="true">ajouter</button>
        </div>
    </form>

    <% for (Ad ad : ((IAdDao) pageContext.getSession().getAttribute("adDao")).getAllAds()) { %>
    <form class="row form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
        <div class="col-sm-2">
            <%= ad.getTitle() %>
        </div>
        <div class="col-sm-2">
            <%= ad.getDescription() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getStatus().name().toLowerCase() %>
        </div>
        <div class="col-sm-2">
            <%= ad.getSeller() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getPrice() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getSoldAt() != null ? ad.getSoldAt() : "" %>
        </div>
        <div class="col-sm-1">
            <%= ad.getViewNumber() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getModificationAt() != null ? ad.getModificationAt() : "" %>
        </div>
        <div class="col-sm-1">
            <input type="hidden" name="adId" value="<%= ad.getId() %>"/>
            <button type="submit" name="adValidate" value="true">valider</button>
            <button type="submit" name="adDelete" value="true">supprimer</button>
        </div>
    </form>
    <% } %>
</div>
</body>
</html>

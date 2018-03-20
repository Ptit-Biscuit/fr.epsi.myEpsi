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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
</head>

<body>
<% if (pageContext.getRequest().getAttribute("userAlreadyExists") != null) { %>
<div class="error">
    <p>Cette adresse mail est déjà utilisée</p>
</div>
<% } %>

<a href="${pageContext.request.contextPath}/welcome">Retour</a>

<br/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">mail</div>
        <div class="col-sm-2">pseudo</div>
        <div class="col-sm-3">mot de passe</div>
        <div class="col-sm-2">date d'inscription</div>
        <div class="col-sm-2">actions</div>
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
            <button type="submit" name="userDelete" value="true">supprimer</button>
        </div>
    </form>
    <% } %>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">titre</div>
        <div class="col-sm-2">description</div>
        <div class="col-sm-1">status</div>
        <div class="col-sm-1">prix</div>
        <div class="col-sm-1">vendu le</div>
        <div class="col-sm-2">acheteur</div>
        <div class="col-sm-1">nombre de vues</div>
        <div class="col-sm-1">modifié le</div>
        <div class="col-sm-1">actions</div>
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
        <div class="col-sm-1">
            <input class="form-control" type="number" name="adPrice" min="0" step="any"/>
        </div>
        <div class="col-sm-1">
            <input type="hidden"/>
        </div>
        <div class="col-sm-2">
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
        <div class="col-sm-1">
            <%= ad.getPrice() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getSoldAt() %>
        </div>
        <div class="col-sm-2">
            <%= ad.getBuyer() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getViewNumber() %>
        </div>
        <div class="col-sm-1">
            <%= ad.getModificationAt() %>
        </div>
        <div class="col-sm-1">
            <input type="hidden" name="adId" value="<%= ad.getId() %>"/>
            <button type="submit" name="adDelete" value="true">supprimer</button>
        </div>
    </form>
    <% } %>
</div>
</body>
</html>

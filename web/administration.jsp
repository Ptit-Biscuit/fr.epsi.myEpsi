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

<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
    <div class="table-responsive">
        <div class="table table-bordered table-striped table-highlight">
            <div class="">
                <tr>
                    <td>mail</td>
                    <td>pseudo</td>
                    <td>mot de passe</td>
                    <td>date d'inscription</td>
                    <td>actions</td>
                </tr>
            </div>

            <tbody>
            <tr>
                <td><input class="form-control" type="email" name="mail" placeholder="Adresse mail" maxlength="64"/>
                </td>
                <td><input class="form-control" type="text" name="pseudo" placeholder="Pseudonyme" maxlength="20"/></td>
                <td><input class="form-control" type="password" name="password" placeholder="Mot de passe"
                           minlength="8"/></td>
                <td><input class="form-control" type="password" name="confirm" placeholder="Confirmation mot de passe"
                           minlength="8"/></td>
                <td>
                    <button type="submit" name="userCreation" value="true">ajouter</button>
                </td>
            </tr>

            <% int i = 0;
                for (User user : ((IUserDao) pageContext.getSession().getAttribute("userDao")).getAllUsers()) { %>
            <tr>
                <td><input class="form-control" type="email" name="mail" value="<%= user.getMail() %>"/></td>
                <td><input class="form-control" type="text" name="pseudo" value="<%= user.getPseudo() %>"/></td>
                <td><input class="form-control" type="text" name="password" value="<%= user.getPassword() %>"/></td>
                <td><%= SimpleDateFormat.getInstance().format(user.getSubsciption().getTime()) %>
                </td>
                <td>
                    <input type="hidden" name="oldMail" value="<%= user.getMail() %>"/>
                    <button type="submit" name="userModification" value="true">modifier</button>
                    <button type="submit" name="userDelete" value="true">supprimer</button>
                </td>
            </tr>
            <% }
                i++;
            %>
            </tbody>
        </div>
    </div>
</form>

<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/administration">
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-highlight">
            <thead>
            <tr>
                <th>titre</th>
                <th>description</th>
                <th>status</th>
                <th>vendeur</th>
                <th>prix</th>
                <th>vendu le</th>
                <th>nombre de vues</th>
                <th>modifié le</th>
                <th>actions</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td><input class="form-control" type="text" name="adTitle" maxlength="140"/></td>
                <td><input class="form-control" type="text" name="adDescription" maxlength="255"/></td>
                <td><input type="hidden" name="adStatus" value="<%= EStatus.VALID.ordinal() %>"/></td>
                <td><input class="form-control" type="email" name="adSeller" maxlength="64"/></td>
                <td><input class="form-control" type="number" name="adPrice" min="0" step="any"/></td>
                <td><input type="hidden"/></td>
                <td><input type="hidden"/></td>
                <td><input type="hidden"/></td>
                <td>
                    <button type="submit" name="adCreation" value="true">ajouter</button>
                </td>
            </tr>

            <% for (Ad ad : ((IAdDao) pageContext.getSession().getAttribute("adDao")).getAllAds()) { %>
            <tr>
                <td><%= ad.getTitle() %>
                </td>
                <td><%= ad.getDescription() %>
                </td>
                <td><%= ad.getStatus().name().toLowerCase() %>
                </td>
                <td><%= ad.getSeller() %>
                </td>
                <td><%= ad.getPrice() %>
                </td>
                <td><%= ad.getSoldAt() %>
                </td>
                <td><%= ad.getViewNumber() %>
                </td>
                <td><%= ad.getModificationAt() %>
                </td>
                <td>
                    <input type="hidden" name="adId" value="<%= ad.getId() %>"/>
                    <button type="submit" name="adDelete" value="true">supprimer</button>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>

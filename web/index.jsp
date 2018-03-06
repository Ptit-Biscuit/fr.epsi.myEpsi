<%@ page import="fr.epsi.myEpsi.ApplicationStartup" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean init = ApplicationStartup.isInitialized();
%>

<html>
    <head>
        <meta charset="UTF-8" />
        <% if(!init) { %>
            <meta http-equiv="refresh" content="2, URL=index.jsp" />
        <% } %>

        <title>LeBonKoinKoin</title>

        <%-- TODO add a favicon --%>

        <link rel="stylesheet" type="text/css" href="css/style.css" />
    </head>

    <body>
        <% if(!init) { %>
            <div class="sk-wave">
                <div class="sk-rect sk-rect1"></div>
                <div class="sk-rect sk-rect2"></div>
                <div class="sk-rect sk-rect3"></div>
                <div class="sk-rect sk-rect4"></div>
                <div class="sk-rect sk-rect5"></div>
            </div>

            <div class="message">
                Chargement en cours
            </div>
        <% } else { %>
            <form class="form" id="login" method="post" action="/connection">
                <h1>Connexion</h1>

                <fieldset class="inputs">
                    <input id="mail" name="mail" type="email" placeholder="Adresse mail" maxlength="64" autofocus required />
                    <input id="password" name="password" type="password" placeholder="Mot de passe" minlength="8" required />
                </fieldset>

                <fieldset class="actions">
                    <input type="submit" id="submit" value="Valider" />
                    <input type="reset" id="reset" value="Annuler" />

                    <a href="/subscribe">Inscription</a>
                </fieldset>
            </form>
        <% } %>
    </body>
</html>

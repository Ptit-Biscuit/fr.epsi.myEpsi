<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <% if (!(boolean) pageContext.getSession().getAttribute("isInitialized")) { %>
    <meta http-equiv="refresh" content="2, URL=index.jsp"/>
    <% } %>

    <title>LeBonKoinKoin</title>

    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

    <body>
        <% if(!(boolean) pageContext.getSession().getAttribute("isInitialized")) { %>
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
        <form class="formDisplay" id="login" method="post" action="${pageContext.request.contextPath}/connection">
                <h1>Connexion</h1>

                <fieldset class="inputs">
                    <input id="mail" name="mail" type="email" placeholder="Adresse mail" maxlength="64" autofocus required />
                    <input id="password" name="password" type="password" placeholder="Mot de passe" minlength="8" required />
                </fieldset>

                <fieldset class="actions">
                    <input type="submit" id="submit" value="Valider" />
                    <input type="reset" id="reset" value="Annuler" />

                    <a href="${pageContext.request.contextPath}/subscribe">Inscription</a>
                </fieldset>
            </form>
        <% } %>
    </body>
</html>

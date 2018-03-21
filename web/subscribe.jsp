<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>

    <title>LeBonKoinKoin</title>

    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

    <body>
        <% if((Boolean) pageContext.getRequest().getAttribute("userAlreadyExists")) { %>
            <div class="error">
                <p>Cette adresse mail est déjà utilisée</p>
            </div>
        <% } %>

        <form class="formDisplay" id="subscribe" method="post" action="${pageContext.request.contextPath}/subscribe">
            <h1>Inscription</h1>

            <fieldset class="inputs">
                <input id="mail" name="mail" type="email" placeholder="Adresse mail" maxlength="64" autofocus required />
                <input id="pseudo" name="pseudo" type="text" placeholder="Pseudonyme" maxlength="20"/>
                <input id="password" name="password" type="password" placeholder="Mot de passe" minlength="8" required />
                <input id="confirm" name="confirm" type="password" placeholder="Confirmation mot de passe" minlength="8" required />
            </fieldset>

            <fieldset class="actions">
                <input type="submit" id="submit" value="Valider" />

                <button type="reset" id="back">
                    <a href="${pageContext.request.contextPath}/index.jsp">Retour</a>
                </button>
            </fieldset>
        </form>
    </body>
</html>

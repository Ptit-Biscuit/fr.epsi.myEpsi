<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@ include file="header.jsp" %>

    <body>
        <% if((Boolean) pageContext.getRequest().getAttribute("userAlreadyExists")) { %>
            <div class="error">
                <p>Cette adresse mail est déjà utilisée</p>
            </div>
        <% } %>

        <form class="form" id="subscribe" method="post" action="${pageContext.request.contextPath}/subscribe">
            <h1>Inscription</h1>

            <fieldset class="inputs">
                <input id="mail" name="mail" type="email" placeholder="Adresse mail" maxlength="64" autofocus required />
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

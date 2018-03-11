<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%@ include file="header.jsp" %>

    <body>
        <form class="form create" id="createAd" action="${pageContext.request.contextPath}/createAd" method="post">
            <h1>Création d'une annonce</h1>

            <fieldset class="inputs create">
                <input id="adTitle" name="adTitle" type="text" placeholder="Titre de l'annonce" maxlength="100" autofocus required />
                <input id="adDescription" name="adDescription" type="text" placeholder="Description" maxlength="100" required />
                <input id="adPrice" name="adPrice" type="number" placeholder="Prix" min="0" step="any" required />
            </fieldset>

            <fieldset class="actions">
                <input type="submit" id="submit" value="Valider" />

                <button type="reset" id="back">
                    <a href="${pageContext.request.contextPath}/welcome">Retour</a>
                </button>
            </fieldset>
        </form>
    </body>
</html>

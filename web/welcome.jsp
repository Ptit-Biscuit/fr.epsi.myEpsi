<%@ page import="fr.epsi.myEpsi.models.beans.User" %>
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
            Welcome <%= ((User) pageContext.getSession().getAttribute("user")).getMail() %> !!
        </div>
    </body>
</html>

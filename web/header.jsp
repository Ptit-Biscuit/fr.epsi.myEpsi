<head>
    <meta charset="UTF-8" />
    <% if(!(boolean) pageContext.getSession().getAttribute("isInitialized")) { %>
        <meta http-equiv="refresh" content="2, URL=index.jsp" />
    <% } %>

    <title>LeBonKoinKoin</title>

    <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>